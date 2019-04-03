package com.spark.demo.scala.chapter05

/**
  * @author yangqian
  * @date 2018-11-27
  */
class Counter {
  // 字段必须要进行初始化
  private var value = 0
  // 方法默认为公有
  def increment(): Unit = {
    value += 1
  }
  // 返回当前值
  def current() = value
  def isLess(other : Counter) = value < other.value
}
