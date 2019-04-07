package com.spark.demo.scala.demo04

/**
  * @author yangqian
  * @date 2019-04-07
  */
object AbstractApp {

  def main(args: Array[String]): Unit = {
    val student2 = new Student2()
    student2.speak
    println("student2 : " + student2.name + " " + student2.age)
  }

}

/**
  * 类的一个或者多个方法没有完整的实现（只有定义没有实现）
  */
abstract class Person2 {

  def speak

  val name: String
  val age : Int

}

class Student2 extends Person2 {


  override def speak: Unit = {
    println("speak Student2")
  }

  override val name: String = "PK"
  override val age: Int = 18

}
