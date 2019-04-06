package com.spark.demo.scala.spark.demo

import org.apache.spark.sql.SparkSession

/**
  * @author yangqian
  * @date 2019-04-03
  */
object SparkSessionApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkSessionApp")
      .master("local[2]")
      .getOrCreate()
//    spark.read.format("json")
    val people = spark.read.json("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/people.json")
    people.show()

    spark.stop()
  }

}
