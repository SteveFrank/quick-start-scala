package com.spark.demo.scala.demo04

/**
  * @author yangqian
  * @date 2019-04-07
  */
object ConstructorApp {

  def main(args: Array[String]): Unit = {
    val person = new Person("zhangsan", 20)
    println(person.name)
    println(person.age)

    val person2 = new Person("lisi", 25, "M")
    println("person2: " + person2.name + " " + person2.age + " " + person2.gender + " " + person2.school)

    val student = new Student("student", 18, "Math")
    println("student: "
      + student.name + " "
      + student.age + " "
      + student.school + " "
      + student.major)
  }

}

/**
  * 主构造器
 @param name
  * @param age
  */
class Person(val name: String, val age: Int) {

  println("Person Constructor enter ... ...")

  val school = "ust"
  var gender : String = _

  println("Person Constructor leave ... ...")

  /**
    * 附属构造器
    * @param name
    * @param age
    * @param gender
    */
  def this(name: String, age: Int, gender: String) {
    // 附属构造器的第一行代码必须要调用主构造器或者其他的构造器方法
    this(name, age)
    this.gender = gender
  }

}

class Student(name: String, age: Int, var major: String) extends Person(name, age) {
  println("Student Constructor enter ... ...")

  override val school: String = "peking"

  override def toString: String = "student override def toString :" + school


  println("Student Constructor leave ... ...")



}
