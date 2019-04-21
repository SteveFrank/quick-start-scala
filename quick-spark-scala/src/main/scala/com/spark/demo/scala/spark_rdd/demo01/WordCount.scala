package com.spark.demo.scala.spark_rdd.demo01

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yangqian
  * @date 2019-04-14
  */
object WordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount")
    val sc = new SparkContext(conf)
    // textFile 产生两个RDD HadoopRDD -> MapPartitionRDD
    sc.textFile(args(0))
      // 产生一个RDD : MapPartitionRDD
      .flatMap(_.split(" "))
      // 产生一个RDD : MapPartitionRDD
      .map((_, 1))
      // 产生一个RDD : ShuffledRDD
      .reduceByKey(_+_)
      .sortBy(_._2, false)
      // 产生一个RDD : MapPartitionRDD
      .saveAsTextFile(args(1))
    sc.stop()
  }

}
