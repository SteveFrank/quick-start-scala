package com.spark.demo.scala.chapter05

import scala.beans.BeanProperty

/**
  * @author yangqian
  * @date 2018-11-27
  */
class Person {

  // 利用注解的方式生成四种get与set方法
  @BeanProperty var name : String = _

  // 变成私有并改名
  private var privateAge : Int = 0
  // getter / setter 方法处理
  def age: Int = privateAge
  def age_(newValue : Int) : Unit = {
    if (newValue > privateAge) privateAge = newValue
  }

  // 使用辅助构造器进行处理
  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, age: Int) {
    this(name)
    this.age_(age)
  }

  override def toString: String = this.name + " --> " + this.age

}

