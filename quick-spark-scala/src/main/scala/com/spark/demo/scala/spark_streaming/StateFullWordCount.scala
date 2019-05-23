package com.spark.demo.scala.spark_streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @author yangqian
  * @date 2019-04-21
  */
object StateFullWordCount {


  /**
    * (hello, 1),(hello, 1),(tom, 1)
    * (hello, Seq(1,1)), (tom, Seq(1,1,1))
    * Seq 批次的某个单词次数
    * Option[Int] 上一次的结果
    */
  val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    //iter.flatMap(it=>Some(it._2.sum + it._3.getOrElse(0)).map(x=>(it._1,x)))
    //iter.map{case(x,y,z)=>Some(y.sum + z.getOrElse(0)).map(m=>(x, m))}
    //iter.map(t => (t._1, t._2.sum + t._3.getOrElse(0)))
    iter.map{ case(word, current_count, history_count) => (word, current_count.sum + history_count.getOrElse(0)) }
  }


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("StateFullWordCount")
      .setMaster("local[2]")
    val sc   = new SparkContext(conf)
    sc.setCheckpointDir("hdfs://master:9000/checkpoint/spark")
    /**
      * StreamingContext
      * 5s 产生RDD的时间间隔
      *
      */
    val ssc  = new StreamingContext(sc, Seconds(5))
    /**
      * 接收数据
      */
    val ds   = ssc.socketTextStream("localhost", 8888)
    // DStream是一个特殊的RDD
    // 批次内的聚合
    // updateStateByKey 必须设置checkpoint
    val result = ds
      .flatMap(_.split(" "))
      .map((_, 1))
      .updateStateByKey(updateFunc, new HashPartitioner(sc.defaultParallelism), true)

    // result处理
    result.print()

    // 启动 等待结束
    ssc.start()
    ssc.awaitTermination()
  }

}
