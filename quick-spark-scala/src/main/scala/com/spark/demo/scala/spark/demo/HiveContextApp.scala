package com.spark.demo.scala.spark.demo

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yangqian
  * @date 2019-04-01
  */
object HiveContextApp {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    // 在测试或者生产环境汇总，APPNAME和Master通过脚本进行指定
//    sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sparkContext = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sparkContext)

    val emp_table = hiveContext.table("emp").show()

    sparkContext.stop()

  }

}
