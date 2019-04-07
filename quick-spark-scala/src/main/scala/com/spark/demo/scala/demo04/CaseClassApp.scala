package com.spark.demo.scala.demo04

/**
  * 通常会使用在模式匹配上
  *
  * @author yangqian
  * @date 2019-04-07
  */
object CaseClassApp {
  def main(args: Array[String]): Unit = {
    println(Dog("wangcai").name)
  }
}

/**
  * case class 不用new
  * 主要是用法上的不同
  * @param name
  */
case class Dog(name: String)
