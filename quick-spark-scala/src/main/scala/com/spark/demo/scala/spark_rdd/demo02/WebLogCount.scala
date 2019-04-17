package com.spark.demo.scala.spark_rdd.demo02

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}


/**
  * @author yangqian
  * @date 2019-04-14
  */
object WebLogCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WebLogCount")
      .setMaster("local[2]")

    val sc   = new SparkContext(conf)

    // rdd1 第一次切分
    val rdd1 = sc
      .textFile("hdfs://master:9000/spark_demo/web_log/web.log")
        .map(line => {
          val f = line.split("\t")
          (f(1), 1)
        })


    // rdd2 计算访问次数
    val rdd2 = rdd1.reduceByKey(_+_)


    val rdd3 = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, url, t._2)
    })

    val rdd4 = rdd3.groupBy(_._1).mapValues(it => {
      // 排序取前三名
      it.toList.sortBy(_._3).reverse.take(3)
    })

    println(rdd4.collect().toBuffer)


    sc.stop()
  }

}
