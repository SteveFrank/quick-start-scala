package com.spark.demo.scala.chapter08

import scala.beans.BeanProperty

/**
  * @author yangqian
  * @date 2018-12-10
  */
class Creature {

  @BeanProperty val range: Int = 10
  val env: Array[Int] = new Array[Int](range)

}
