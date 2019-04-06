package com.spark.demo.scala.spark.project

import com.spark.demo.scala.spark.utils.DateUtils
import org.apache.spark.sql.SparkSession

/**
  * 第一步清洗工作：抽取出所需要的指定列的数据
  *
  * @author yangqian
  * @date 2019-04-06
  */
object SparkStatFormatJob {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("SparkStatFormatJob")
      .master("local[2]")
      .getOrCreate()

    val accessRdd = spark.sparkContext.textFile("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/20000_access.log")

//    accessRdd.take(10).foreach(println)

    accessRdd.map(line => {
      val splits  = line.split(" ")
      val ip      = splits(0)
      /**
        * 原始日志的第三个和第四个字段拼接 为完整的访问时间
        * [10/Nov/2016:00:01:02 +0800]
        * 需要进行转化 作为一个正常的时间展示
        */
      val time    = splits(3) + " " + splits(4)
      val url     = splits(11).replaceAll("\"", "")
      /**
        * 流量字段
        */
      val traffic = splits(9)


      (ip, DateUtils.parse(time), url, traffic)

      DateUtils.parse(time) + "\t" + url + "\t" + traffic + "\t" + ip
    }).saveAsTextFile("hdfs://hadoop001:8020/traffic/etl_access_log")

    spark.stop()

  }

}
