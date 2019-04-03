package com.spark.demo.scala.chapter08

/**
  * @author yangqian
  * @date 2018-12-10
  */
class Test {

}

object Test {
  val ant = new Ant
  val bank = new BankAccount(10)
  val new_bank = new NewBankAccount(10)
  def main(args: Array[String]): Unit = {
    println(bank.deposit(20))
    println(bank.withdraw(5))

    println(new_bank.deposit(20))
    println(new_bank.withdraw(5))
  }
}
