package com.spark.demo.scala.spark.utils

import org.apache.spark.sql.{Row, types}
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

/**
  * 访问日志转换工具类
  *
  * （输入 ===>>> 输出）
  *
  * @author yangqian
  * @date 2019-04-06
  */
object AccessConvertUtil {

  val struct = types.StructType(
    Array(
      StructField("url", StringType),
      StructField("cmsType", StringType),
      StructField("cmsId", LongType),
      StructField("traffic", LongType),
      StructField("ip", StringType),
      StructField("city", StringType),
      StructField("time", StringType),
      StructField("day", StringType)
    )
  )

  /**
    * 根据每一行输入的信息转换成为输出的样式
    * @param log
    */
  def parseLog(log:String) = {
    try {
      val splits = log.split("\t")

      val url = splits(1)
      val traffic = splits(2).toLong
      val ip = splits(3)

      val domain = "http://www.imooc.com/"
      val cms = url.substring(url.indexOf(domain) + domain.length)

      val cmsTypeId = cms.split("/")
      var cmsType = ""
      var cmsId = 0l

      if (cmsTypeId.length > 1) {
        cmsType = cmsTypeId(0)
        cmsId = cmsTypeId(1).toLong
      }

      val city = IpUtils.getCity(ip)
      val time = splits(0)
      val day = time.substring(0, 10).replaceAll("-", "")

      // 这个Row里面的字段需要和struct中的字段相互对应
      Row(url, cmsType, cmsId, traffic, ip, city, time, day)
    } catch {
      case e: Exception => Row(0)
    }

  }

  def main(args: Array[String]): Unit = {

  }
}
