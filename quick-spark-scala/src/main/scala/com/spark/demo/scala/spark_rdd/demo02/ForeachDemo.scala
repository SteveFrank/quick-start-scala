package com.spark.demo.scala.spark_rdd.demo02

import com.spark.demo.scala.spark.utils.MySQLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author yangqian
  * @date 2019-04-14
  */
object ForeachDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("ForeachName")
      .setMaster("local[2]")
    val sc   = new SparkContext(conf)

    val rdd1 = sc.parallelize(List(1,2,3,4,5,6,7,8,9), 3)
    rdd1.foreachPartition(
      it => {
        // 每个分区创建一个连接
//        val connection = MySQLUtils.getConnections()
      }
    )
    sc.stop()
  }

}
