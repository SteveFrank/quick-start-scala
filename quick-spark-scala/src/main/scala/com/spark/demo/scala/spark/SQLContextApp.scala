package com.spark.demo.scala.spark

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yangqian
  * @date 2019-04-01
  */
object SQLContextApp {

  def main(args: Array[String]): Unit = {
    val path = args(0)

    val sparkConf = new SparkConf()
    // 在测试或者生产环境汇总，APPNAME和Master通过脚本进行指定
//    sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sparkContext = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sparkContext)

    val people = sqlContext.read.format("json").load(path)
    people.printSchema()
    people.show()

    sparkContext.stop()

  }

}
