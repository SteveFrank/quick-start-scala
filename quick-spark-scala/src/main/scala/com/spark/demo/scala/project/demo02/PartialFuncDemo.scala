package com.spark.demo.scala.project.demo02

/**
  * @author yangqian
  * @date 2019-04-20
  */
object PartialFuncDemo {
  // 偏函数 [输入,输出]
  def func1: PartialFunction[String, Int] = {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def func2(num: String) : Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def main(args: Array[String]) {
    println(func1("one"))
    println(func2("one"))
  }

}
