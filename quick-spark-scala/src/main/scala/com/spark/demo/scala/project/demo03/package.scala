package com.spark.demo.scala.project

/**
  * @author yangqian
  * @date 2019-04-20
  */
package object ManageObjects {
  case class SubmitTask(filename: String)
  case class ResultTask(map: Map[String, Int])
  case object StartTask
  case object StopTask
}
