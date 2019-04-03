package com.spark.demo.scala.chapter08

/**
  * @author yangqian
  * @date 2018-12-10
  */
class BankAccount (initialBalance: Double) {
  private var balance = initialBalance
  def deposit(amount: Double) = {
    balance += amount
    balance
  }
  def withdraw(amount: Double) = {
    balance -= amount
    balance
  }
}
