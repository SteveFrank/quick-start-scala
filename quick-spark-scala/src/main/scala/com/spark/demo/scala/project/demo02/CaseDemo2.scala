package com.spark.demo.scala.project.demo02

import scala.util.Random

/**
  * @author yangqian
  * @date 2019-04-20
  */

case class SubmitTask(id: String, name: String)
case class HeartBeat(time: Long)
case object CheckTimeOutTask

object CaseDemo2 extends App {

  val arr = Array(CheckTimeOutTask, HeartBeat(12333), SubmitTask("0001", "task-0001"))

  arr(Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) =>
      //前面需要加上s, $id直接取id的值
      println(s"$id, $name")
    case HeartBeat(time) =>
      println(time)
    case CheckTimeOutTask =>
      println("check")
  }


}
