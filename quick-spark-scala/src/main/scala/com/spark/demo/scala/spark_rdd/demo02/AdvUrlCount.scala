package com.spark.demo.scala.spark_rdd.demo02

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}


/**
  * @author yangqian
  * @date 2019-04-14
  */
object AdvUrlCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("AdvUrlCount")
      .setMaster("local[2]")

    val sc   = new SparkContext(conf)

    // rdd1 第一次切分
    val rdd1 = sc
      .textFile("hdfs://master:9000/spark_demo/web_log/web.log")
        .map(line => {
          val f = line.split("\t")
          (f(1), 1)
        })
    println(rdd1.collect().toBuffer)

    // rdd2 计算访问次数
    val rdd2 = rdd1.reduceByKey(_+_)
    println(rdd2.collect().toBuffer)

    val rdd3 = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, url, t._2)
    })
    val rddjava = rdd3.filter(_._1 == "java.itcast.cn")
    val sortedjava = rddjava.sortBy(_._3, false).take(3)

    println(sortedjava.toBuffer)


    sc.stop()
  }

}
