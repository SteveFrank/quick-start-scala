package com.spark.demo.scala.demo04

/**
  * @author yangqian
  * @date 2019-04-07
  */
object SimpleObjectApp {

  def main(args: Array[String]): Unit = {
    val people = new People()
    people.name = "James"
    println(people.name + " == " + people.age)

    println("invoke eat method : " + people.eat())

    people.watchFootball("Barcelona")

    people.printInfo()
  }

}

class People {

  // 定义属性 _ 为占位符（必须是可变变量才可以使用占位符）
  var name: String = _
  val age : Int    = 10

  // private 私有修饰
//  括号中代表的是对应的包名等信息，可以自行定义该信息
//  private [people] val gender = "male"
  private [this] val gender = "male"

  def printInfo(): Unit = {
    println("gender : " + gender)
  }

  // 定义方法
  def eat(): String = {
    name + " eat ... ..."
  }

  def watchFootball(teamName: String): Unit = {
    println(name + " is watching match of " + teamName)
  }

}
