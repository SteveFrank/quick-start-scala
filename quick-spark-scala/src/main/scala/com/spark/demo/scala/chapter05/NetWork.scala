package com.spark.demo.scala.chapter05

import scala.collection.mutable.ArrayBuffer

/**
  * @author yangqian
  * @date 2018-11-27
  */
class NetWork {

  class Member (val name : String) {
    val contacts = new ArrayBuffer[Member]()
  }

  private val members = new ArrayBuffer[Member]()

  def join(name : String)= {
    val m = new Member(name)
    members += m
    m
  }

}

object Test {
  def main(args: Array[String]): Unit = {
    val chatter = new NetWork
    val myFace  = new NetWork

    val fred  = chatter.join("Fred")
    val wilma = chatter.join("wilma")

    fred.contacts += wilma
    val barney = myFace.join("Barney")

  }
}
