package com.spark.demo.scala.chapter06

import com.spark.demo.scala.chapter06.TrafficLightColor.TrafficLightColor


/**
  * @author yangqian
  * @date 2018-11-27
  */
object Test {

  def main(args: Array[String]): Unit = {

    println(doWhat(TrafficLightColor.Red))
    println(doWhat(TrafficLightColor.Yellow))
    println(doWhat(TrafficLightColor.Green))

    for (c <- TrafficLightColor.values) println(c.id + " " + c)

  }

  def doWhat(color: TrafficLightColor) = {
    if (color == TrafficLightColor.Red)
      "STOP"
    else if (color == TrafficLightColor.Yellow)
      "Hurry Up"
    else
      "Go"
  }

}
