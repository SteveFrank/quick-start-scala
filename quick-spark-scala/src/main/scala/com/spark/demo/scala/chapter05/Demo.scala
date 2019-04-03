package com.spark.demo.scala.chapter05

object Demo {

  def main(args: Array[String]): Unit = {
    // 创建counter对象
    val myCounter = new Counter
    myCounter.increment()
    myCounter.increment()
    myCounter.increment()
    println(myCounter.current())

    val person = new Person
    person.setName("Admin")
    person.getName
    person.age_(12)

    println(person.getName + " " + person.age)

    val person1 = new Person("Alice")
    println(person1)

    val person2 = new Person("Ben", 20)
    println(person2)

    val animal = new Animal("tom", 2 , "China")
    println(animal)

  }

}