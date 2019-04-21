package com.spark.demo.scala.project

import com.spark.demo.scala.project.rpc.RemoteMessage

/**
  * @author yangqian
  * @date 2019-04-20
  */
package object ManageObjects {
  case class SubmitTask(filename: String) extends RemoteMessage
  case class ResultTask(map: Map[String, Int]) extends RemoteMessage
  case object StartTask extends RemoteMessage
  case object StopTask extends RemoteMessage
}
