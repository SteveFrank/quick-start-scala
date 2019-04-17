package com.spark.demo.scala.project.demo02

/**
  * @author yangqian
  * @date 2019-04-15
  */
object HelloScala {

  /**
    * 一个参数的函数实例
    */
  val func1 = (x: Int) => x * 10

  def m1 (f: Int => Int) : Int = {
    f(3)
  }

  /**
    * 元组的函数实例
    */
  val fun2:(Double, Int) => (Int, Double) = {
    (x, y) => (y, x)
  }

  def m2(name: String) : Unit = {
    println(name)
  }

  def main(args: Array[String]) = {
    m2("laoduan")

    val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")
    // 方式一
    lines.map(_.split(" ")).flatten
    // 方式二
    val result = lines
      .flatMap(_.split(" "))
      .map((_, 1))
      .groupBy(_._1)
      .map(t => (t._1, t._2.size))

    print(result.toList.sortBy(_._2).reverse)

  }

}
