package com.spark.demo.scala.project.pro01

/**
  * @author yangqian
  * @date 2019-04-20
  */
class HighFunc {

  val func: Int => Int = {
    x => x * x
  }

  def multipy(x : Int) = {

  }

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5, 6)
    val a1 = arr.map(func)
  }

}
