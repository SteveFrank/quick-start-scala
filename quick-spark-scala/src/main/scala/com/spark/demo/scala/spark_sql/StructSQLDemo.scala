package com.spark.demo.scala.spark_sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SparkSession}

/**
  * @author yangqian
  * @date 2019-04-21
  */
object StructSQLDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("SQLDemo")
    val sc   = new SparkContext(conf)
    val sqlContext = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()

    System.setProperty("user.name", "bigdata")

    val rdd = sc
      .textFile("hdfs://master:9000/spark_demo/sparksql/person.txt")
      .map(_.split(","))
      .map(
        p => Row(p(0).toInt, p(1).toString, p(2).toInt)
      )

    /**
      * DataFrame
      */
    val personDF = sqlContext.createDataFrame(rdd, personStructType)

    personDF.createOrReplaceTempView("t_person")
    sqlContext.sql("select * from t_person").show()

    sqlContext.stop()
    sc.stop()

  }

}
