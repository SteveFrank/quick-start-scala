package com.spark.demo.scala.demo04

/**
  * 伴生类 与 伴生对象
  * 如果有一个class 还有一个与class同名的object
  * 可以称这个object是class的伴生对象 class是object的伴生类
  *
  * 说明object 本身就是一个单例的对象
  *
  * @author yangqian
  * @date 2019-04-07
  */
object ApplyApp {
  def main(args: Array[String]): Unit = {
//    for (i <- 1 to 10) {
//      ApplyTest.incr
//    }
//
//    println(ApplyTest.count)

    val b = ApplyTest() // ===>> 默认走object中的apply方法, Object.apply
    println(b)

    println(" ~~~~~~~~~ ")
    val c = new ApplyTest()
    println(c)

  }
}


class ApplyTest {

}

object ApplyTest {
  println("ApplyTest enter ... ...")

  var count = 0
  def incr = {
    count = count + 1
  }

  def apply() = {
    println("Object ApplyTest apply ... ...")
    new ApplyTest
  }

  println("ApplyTest leave ... ...")
}