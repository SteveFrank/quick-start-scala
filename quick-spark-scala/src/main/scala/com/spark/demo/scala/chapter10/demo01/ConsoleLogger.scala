package com.spark.demo.scala.chapter10.demo01

/**
  * 如果需要的特质不止一种，可以使用with关键字来添加额外的特质
  * @author yangqian
  * @date 2018-12-12
  */
class ConsoleLogger extends Logger with Cloneable with Serializable {

  override def log(msg: String): Unit = {
    println(msg)
  }

}

object ConsoleLogger {
  def main(args: Array[String]): Unit = {
//    val consoleLogger = new ConsoleLogger
//    consoleLogger.log("123")

  }
}
