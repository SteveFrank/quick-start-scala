package com.spark.demo.scala.spark_sql

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yangqian
  * @date 2019-04-21
  */
object SQLDemo {

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
      .map(line => {
        val fields = line.split(",")
        Person(fields(0).toLong, fields(1).toString, fields(2).toInt)
      })

    /**
      * DataFrame
      */
    import sqlContext.implicits._
    val personDF = rdd.toDF()

    personDF.createOrReplaceTempView("t_person")
    sqlContext.sql("select * from t_person").show()

    sqlContext.stop()
    sc.stop()

  }

}
