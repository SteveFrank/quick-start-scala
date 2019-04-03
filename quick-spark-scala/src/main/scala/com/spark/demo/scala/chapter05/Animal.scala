package com.spark.demo.scala.chapter05

/**
  * @author yangqian
  * @date 2018-11-27
  */
class Animal (name: String, age: Int, from: String) {

  override def toString: String = this.name + " -- " + this.age + " -- " + from

}
