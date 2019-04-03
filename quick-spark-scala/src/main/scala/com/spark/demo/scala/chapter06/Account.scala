package com.spark.demo.scala.chapter06

/**
  * @author yangqian
  * @date 2018-11-27
  */
class Account private (val id: Int, initialBalance: Double) {

  private var balance = initialBalance

  def deposit(amount : Double): Unit = {
    balance += amount
  }

  override def toString: String = "id : " + id + ", balance : " + balance
}

object Account { // 伴生对象

  def apply(initialBalance : Double) = {
    new Account(newUniqueNumber, initialBalance)
  }

  private var lastNumber = 0

  def newUniqueNumber = {
    lastNumber += 1
    lastNumber
  }

  def main(args: Array[String]): Unit = {
    println(Account(1000.0))
    println(Account(1001.0))
    println(Account(1002.0))
    println(Account(1003.0))
    println(Account(1004.0))
    println(Account(1005.0))
  }

}