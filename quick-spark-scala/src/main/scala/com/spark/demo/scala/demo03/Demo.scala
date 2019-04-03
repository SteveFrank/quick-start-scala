package com.spark.demo.scala.demo03

/**
  * @author yangqian
  * @date 2019-03-25
  */
class Demo {
  def max(x: Int, y: Int) : Int = {
    if (x > y)
      x
    else
      y
  }

  def add(x: Int, y: Int) : Int = {
    x + y
  }

  def sayHello() : Unit = {
    print("hello world")
  }

  def speed(distance:Float, time:Float): Float = {
    distance/time
  }

  def sum1(a: Int, b: Int, c:Int): Int = {
    a + b + c
  }

  def sum2(numbers: Int *) = {
    var result = 0
    for (number <- numbers) {
      result += number
    }
  }

  def test01(): Unit = {
    for(i <- 1 to 10 if i % 2 == 0) {
      println(i)
    }
  }

}


object Demo {

  def main(args: Array[String]): Unit = {
    val demo03 = new Demo
    print(demo03.max(1, 2))
    print(demo03.add(2, 4))
    print(demo03.speed(distance = 10, time = 1000))
    demo03.test01()


    val courses = Array("Hadoop", "Spark SQL", "Spark Streaming", "Storm", "Scala")
    for (course <- courses) {
      print(course)
    }
  }

}
