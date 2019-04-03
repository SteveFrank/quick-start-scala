package com.spark.demo.scala.chapter06

/**
  * @author yangqian
  * @date 2018-11-27
  */
object DoNothingAction extends UndoableAction ("Do nothing") {

  override def undo(): Unit = {

    println("undo ...")
  }

  override def redo(): Unit = {

    println("redo ...")
  }

  def main(args: Array[String]): Unit = {
    DoNothingAction.undo()
  }

}
