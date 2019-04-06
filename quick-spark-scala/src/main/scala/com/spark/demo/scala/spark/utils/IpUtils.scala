package com.spark.demo.scala.spark.utils

import com.ggstar.util.ip.IpHelper

/**
  * @author yangqian
  * @date 2019-04-06
  */
object IpUtils {

  def getCity(ip: String): String = {

    IpHelper.findRegionByIp(ip)

  }

  def main(args: Array[String]): Unit = {

    println(getCity("218.22.9.56"))

  }

}
