package com.spark.demo.scala.spark.demo

import org.apache.spark.sql.SparkSession

/**
  * @author yangqian
  * @date 2019-04-06
  */
object ParquetApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("ParquetApp")
      .master("local[2]")
      .getOrCreate()

    /**
      * spark 的默认处理format为parquet
      *
      * val DEFAULT_DATA_SOURCE_NAME = SQLConfigBuilder("spark.sql.sources.default")
      * .doc("The default data source to use in input/output.")
      * .stringConf
      * .createWithDefault("parquet")
      */
    val userDF = spark.read
      .format("parquet")
      .load("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/users.parquet")



    // spark SQL 参数设置
    spark.sqlContext.setConf("spark.sql.shuffle.partitions", "10")

    userDF.printSchema()
    userDF.show()

    spark.stop()

  }

}
