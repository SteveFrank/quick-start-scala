package com.spark.demo.scala.demo05

/**
  * @author yangqian
  * @date 2019-04-07
  */
object ListApp extends App {

  val l = List(1, 2, 3, 4)
  println(l)

  val l5 = scala.collection.mutable.ListBuffer[Int]()
  l5 += 2
  l5 += (3, 4, 5)
  l5 ++= List(6,7,8,9,10)

  println(l5)

}
