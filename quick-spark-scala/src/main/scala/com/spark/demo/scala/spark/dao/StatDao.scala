package com.spark.demo.scala.spark.dao

import java.sql.{Connection, PreparedStatement}

import com.spark.demo.scala.spark.utils.MySQLUtils

import scala.collection.mutable.ListBuffer

/**
  * @author yangqian
  * @date 2019-04-06
  */
object StatDao {

  /**
    * 批量保存DayVideoAccessStat
    * @param list
    */
  def insertDayVideoAccessTopN(list: ListBuffer[DayVideoAccessStat]): Unit = {
    var connection: Connection = null
    var pstmt: PreparedStatement = null

    try {
      connection = MySQLUtils.getConnections()
      connection.setAutoCommit(false)
      val sql = "insert into day_video_access_topn_stat(day, cms_id, times) values (?, ?, ?)"
      pstmt = connection.prepareStatement(sql)

      for (ele <- list) {
        pstmt.setString(1, ele.day)
        pstmt.setLong(2, ele.cmsId)
        pstmt.setLong(3, ele.times)
        pstmt.addBatch()
      }

      pstmt.executeBatch()
      connection.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(connection, pstmt)
    }


  }

  def main(args: Array[String]): Unit = {

  }
}
