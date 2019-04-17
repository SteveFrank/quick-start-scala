package com.spark.demo.scala.spark.utils

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
  * @author yangqian
  * @date 2019-04-06
  */
object MySQLUtils {

  def getConnections(): Connection = {
    DriverManager
      .getConnection("jdbc:mysql://slave01:3306/imooc_project?user=root&password=root")
  }

  def release(connection: Connection, pstmt: PreparedStatement): Unit = {
    try {
      if (pstmt != null) {
        pstmt.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println(getConnections())
  }

}
