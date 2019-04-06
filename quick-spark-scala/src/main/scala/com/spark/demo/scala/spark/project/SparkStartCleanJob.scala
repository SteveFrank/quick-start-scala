package com.spark.demo.scala.spark.project

import com.spark.demo.scala.spark.utils.AccessConvertUtil
import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * @author yangqian
  * @date 2019-04-06
  */
object SparkStartCleanJob {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("SparkStartCleanJob")
      .master("local[2]")
      .getOrCreate()

    val accessRdd = spark.sparkContext.textFile("/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/access.log")
    accessRdd.take(10).foreach(println)

    // RDD ==>> DF
    val accessDF = spark.createDataFrame(
      accessRdd.map(x => AccessConvertUtil.parseLog(x)),
      // schema info
      AccessConvertUtil.struct
    )

    accessDF.printSchema()
    accessDF.show()

    // 调优点 控制文件的输出大小
    accessDF.coalesce(1).write
      .format("parquet")
      .mode(SaveMode.Overwrite)
      // 分区
      .partitionBy("day")
      .save("/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/SparkStartCleanJobResult")

    spark.stop()

  }

}
