package com.spark.demo.scala.spark.project

import com.spark.demo.scala.spark.dao.{DayVideoAccessStat, StatDao}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

import scala.collection.mutable.ListBuffer

/**
  * TopN 的统计Spark任务
  * 
  * @author yangqian
  * @date 2019-04-06
  */
object TopNStatJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("TopNStatJob")
      .config("spark.sql.sources.partitionColumnTypeInference.enabled", "false")
      .master("local[2]")
      .getOrCreate()
    
    val accessDF = spark.read.format("parquet").load("/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/SparkStartCleanJobResult")

    accessDF.printSchema()
    accessDF.show(false)

    try {
      // 最受欢迎的课程信息
      videoAccessTopNStat(spark, accessDF)
    } catch {
      case e: Exception => e.printStackTrace()
    }
    spark.stop()
  }

  /**
    * 最受欢迎的TopN课程
    * @param spark
    * @param accessDF
    */
  def videoAccessTopNStat(spark: SparkSession, accessDF: DataFrame): Unit = {
    // 隐式转换
//    import spark.implicits._
//    val videoAccessTopNDF = accessDF
//      .filter($"day" === "20170511" && $"cmsType" === "video")
//      .groupBy("day", "cmsId")
//      .agg(count("cmsId").as("times"))
//      .orderBy($"times".desc)
//
//    videoAccessTopNDF.show(false)

    accessDF.createOrReplaceTempView("access_logs")
    val videoAccessTopNDF = spark.sql(
      "select day, cmsId, count(1) as times from access_logs " +
      "where day = '20170511' and cmsType = 'video' " +
      "group by day, cmsId order by times desc")
    videoAccessTopNDF.show(false)

    // 统计结果写入到mysql中
    videoAccessTopNDF.foreachPartition(partitionRecords => {
      val list = new ListBuffer[DayVideoAccessStat]

      partitionRecords.foreach(info => {
        val day = info.getAs[String]("day")
        val cmsId = info.getAs[Long]("cmsId")
        val times = info.getAs[Long]("times")

        list.append(DayVideoAccessStat(day, cmsId, times))
      })
      StatDao.insertDayVideoAccessTopN(list)
    })

  }

}
