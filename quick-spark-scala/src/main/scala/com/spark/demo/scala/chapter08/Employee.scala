package com.spark.demo.scala.chapter08

import com.spark.demo.scala.chapter05.Person

import scala.beans.BeanProperty

/**
  * @author yangqian
  * @date 2018-11-27
  */
class Employee extends Person {

  val salary = 10

  override def toString: String = super.toString + ", [salary=" + salary + "]"

}

object Employee {
  def main(args: Array[String]): Unit = {

    val employee = new Employee
    employee.setName("DEMO")
    employee.age_(10)
    println(employee)

    val alien = new Person("Fred") {
      @BeanProperty def greeting = "Greeting, Earthling! My name is Fred."
    }

    println(alien)
    println(alien.greeting)

  }
}
