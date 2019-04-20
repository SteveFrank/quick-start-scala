package com.spark.demo.scala.project.demo02

/**
  * @author yangqian
  * @date 2019-04-18
  */
class Person {

  val id = "9527"

  val name = "huaan"

  private[this] val pop: String = "123"

  def printpop: Unit = {
    println(pop)
  }

}

/**
  * 伴生对象
  * 静态变量
  */
object Person {
  def main(args: Array[String]): Unit = {
    val p = new Person
    println(p.id + " " + p.name)
  }
}
