package com.spark.demo.scala.demo05

/**
  * @author yangqian
  * @date 2019-04-07
  */
object ArrayApp extends App {

  val a = new Array[String](5)
  a.length
  a(1) = "hello"

  val b = Array("hadoop", "spark", "storm")

  val c = Array(2,3,4,5,6,7,8,9)
  c.sum
  c.min
  c.max
  b.mkString(",")

  // 可变数组
  val d = scala.collection.mutable.ArrayBuffer[Int]()
  d += 1
  d += 2
  d += (3,4,5,6,7,8)
  d.insert(8, 10)

  println(d)

}
