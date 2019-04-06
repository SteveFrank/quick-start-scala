package com.spark.demo.scala.spark.demo

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
  * @author yangqian
  * @date 2019-04-05
  */
object DataFrameRDDApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DataFrameRDDApp")
      .master("local[2]")
      .getOrCreate()

//    inferRelection(spark)

    program(spark)

    spark.stop()

  }


  private def program(spark: SparkSession): Unit = {
    val rdd = spark.sparkContext
      .textFile("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/infos.txt")

    val infoRDD = rdd.map(_.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))

    val structType = StructType(
      Array(
        StructField("id", IntegerType, nullable = true),
        StructField("name", StringType, nullable = true),
         StructField("age", IntegerType, nullable = true)
      )
    )

    val infoDF = spark.createDataFrame(infoRDD, structType)

    infoDF.printSchema()
    infoDF.show()

  }


  private def inferRelection(spark: SparkSession): Unit = {
    // RDD ==>> DataFrame
    val rdd = spark.sparkContext
      .textFile("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/infos.txt")

    import spark.implicits._
    val infoDF = rdd
      .map(_.split(","))
      .map(line => Info(line(0).toInt, line(1), line(2).toInt))
      .toDF()

    infoDF.printSchema()
    infoDF.show()

    infoDF.filter(infoDF.col("age") > 30).show()

    println("====")

    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()
  }

  case class Info(id: Int, name: String, age: Int)

}
