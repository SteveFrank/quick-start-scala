package com.spark.demo.scala.chapter06

/**
  * @author yangqian
  * @date 2018-11-27
  */
abstract class UndoableAction (val description : String) {

  def undo() : Unit

  def redo() : Unit

}
