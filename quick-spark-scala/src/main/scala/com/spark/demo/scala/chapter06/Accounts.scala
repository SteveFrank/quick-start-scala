package com.spark.demo.scala.chapter06

/**
  * @author yangqian
  * @date 2018-11-27
  */
class Accounts {

  val id = Accounts.newUniqueNumber

  private var balance : Double = 0.0

  def deposit(amount : Double): Unit = {
    balance += amount
  }

}


object Accounts {

  private var lastNumber = 0

  def newUniqueNumber = {
    lastNumber += 1
    lastNumber
  }

  def main(args: Array[String]): Unit = {
    println(Accounts.newUniqueNumber)
  }

}
