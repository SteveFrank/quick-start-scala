package com.spark.demo.scala.spark_streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author yangqian
  * @date 2019-04-21
  */
object StreamingWordCount {

  def main(args: Array[String]): Unit = {
    Logger.getRootLogger.setLevel(Level.WARN)
    val conf = new SparkConf()
      .setAppName("StreamingWordCount")
      .setMaster("local[2]")
    val sc   = new SparkContext(conf)
    /**
      * StreamingContext
      */
    val ssc  = new StreamingContext(sc, Seconds(5))
    /**
      * 接收数据
      */
    val ds   = ssc.socketTextStream("localhost", 8888)
    // DStream是一个特殊的RDD
    // 批次内的聚合
    val result = ds.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    // result处理
    result.print()

    // 启动 等待结束
    ssc.start()
    ssc.awaitTermination()
  }

}
