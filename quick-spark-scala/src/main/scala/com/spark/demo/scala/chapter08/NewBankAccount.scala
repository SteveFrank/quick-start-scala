package com.spark.demo.scala.chapter08

/**
  * @author yangqian
  * @date 2018-12-10
  */
class NewBankAccount(initBalance: Double) extends BankAccount(initialBalance = initBalance) {

  override def deposit(amount: Double): Double = super.deposit(amount) + 1

  override def withdraw(amount: Double): Double = super.withdraw(amount) + 1
}
