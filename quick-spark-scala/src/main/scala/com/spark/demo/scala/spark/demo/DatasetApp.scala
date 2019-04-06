package com.spark.demo.scala.spark.demo

import org.apache.spark.sql.SparkSession

/**
  * @author yangqian
  * @date 2019-04-06
  */
object DatasetApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DatasetApp")
      .master("local[2]")
      .getOrCreate()

    val path = "file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/sales.csv"

    // Spark如何对于csv文件进行解析
    val df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(path)

    df.printSchema()
    df.show()

    // 需要导入隐式转换

    import spark.implicits._

    // 隐式转换问题
    val ds = df.as[Sales]
    ds.map(line => line.itemId).show()

    spark.stop()

  }

  case class Sales(transactionId: Int, customerId: Int, itemId: Int, amountPaid: Double)

}
