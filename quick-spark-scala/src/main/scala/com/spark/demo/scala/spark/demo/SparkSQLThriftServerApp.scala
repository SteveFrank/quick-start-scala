package com.spark.demo.scala.spark.demo

import java.sql.DriverManager

/**
  * @author yangqian
  * @date 2019-04-03
  */
object SparkSQLThriftServerApp {

  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")

    val conn  = DriverManager.getConnection("jdbc:hive2://hadoop001:14000","hadoop","")
    val pstmt = conn.prepareStatement("select * from emp")
    val rs = pstmt.executeQuery()

    while (rs.next()) {
      println("empno : " + rs.getInt("empno"))
    }

    rs.close()
    pstmt.close()
    conn.close()


  }

}
