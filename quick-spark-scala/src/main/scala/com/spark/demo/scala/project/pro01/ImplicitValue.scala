package com.spark.demo.scala.project.pro01

/**
  * @author yangqian
  * @date 2019-04-20
  */

/**
  * 门面
  * 所有的隐式值和隐式方法必须放置到object中
  */
object Context {
  implicit val a = "laozhao"
}

object ImplicitValue {

  def sayHi()(implicit name: String = "laoduan"): Unit = {
    println(s"hi ~ $name")
  }

  def main(args: Array[String]): Unit = {
    import Context._
    sayHi()
  }

}
