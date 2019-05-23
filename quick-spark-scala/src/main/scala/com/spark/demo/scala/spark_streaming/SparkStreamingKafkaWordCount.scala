package com.spark.demo.scala.spark_streaming

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author yangqian
  * @date 2019-04-27
  */
object SparkStreamingKafkaWordCount {

  val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.flatMap {
      case(x, y ,z) => Some(y.sum + z.getOrElse(0)).map(i => (x, i))
    }
  }

  def main(args: Array[String]): Unit = {
    // Spark相关配置数据
    val conf = new SparkConf().setAppName("SparkStreamingKafkaWordCount").setMaster("local[2]")
    val ssc  = new StreamingContext(conf, Seconds(5))
    ssc.checkpoint("hdfs://master:9000/checkpoint/spark_kafka_01")

    // Kafka相关配置数据
    // zk地址 组名 topic名称 线程数
    val Array(zkQuorum, group, topics, numThreads) = args
    val topMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val data   = KafkaUtils.createStream(ssc, zkQuorum, group, topMap, StorageLevel.MEMORY_AND_DISK_SER)

    // 打印kafka获取到的message
    data.print()

    val words = data.map(_._2).flatMap(_.split(" "))
    val wordCounts = words.map(
      (_, 1)).updateStateByKey(updateFunc, new HashPartitioner(ssc.sparkContext.defaultParallelism),
      rememberPartitioner = true
    )
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()

  }

}
