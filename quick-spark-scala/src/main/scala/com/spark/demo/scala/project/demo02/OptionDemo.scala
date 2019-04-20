package com.spark.demo.scala.project.demo02

/**
  * @author yangqian
  * @date 2019-04-20
  */
object OptionDemo {

  def main(args: Array[String]): Unit = {
    val map = Map("a" -> 1, "b" -> 2)
    val v = map.get("b") match {
      case Some(i) => i
      case None => 0
    }
    println(v)
    //更好的方式
    val v1 = map.getOrElse("c", 0)
    println(v1)
  }

}
