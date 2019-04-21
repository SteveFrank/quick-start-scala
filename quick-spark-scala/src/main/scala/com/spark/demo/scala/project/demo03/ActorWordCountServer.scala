package com.spark.demo.scala.project.demo03

import akka.actor.{Actor, ActorSystem, Props}
import com.spark.demo.scala.project.ManageObjects._
import com.typesafe.config.ConfigFactory

import scala.io.Source

/**
  * @author yangqian
  * @date 2019-04-20
  */

class ActorWordCountServer extends Actor {
  override def receive: Receive = {
    case StartTask =>
      println("ActorWordCountServer Start")
    case SubmitTask(filename) =>
      println(s"receive filename from client : $filename")
      val connect = Source.fromFile(filename)
      val result = connect.getLines()
        .flatMap(_.split(" ")).map((_, 1))
        .toList.groupBy(_._1).mapValues(_.size)
      connect.close()
      /**
        * 1、异步发送
        * 2、! 代表异步发送
        */
      sender ! ResultTask(result)
    case StopTask =>
      context.stop(self)
      context.system.terminate()

  }
}

object ActorWordCountServer {
  /**
    * 创建线程池对象MyFactory，用来创建actor的对象的
    */
//  private val MyFactory = ActorSystem("myFactory")

  /**
    * * 通过MyFactory.actorOf方法来创建一个actor，
    * * 注意，Props方法的第一个参数需要传递我们自定义的HelloActor类，
    * * 第二个参数是给actor起个名字
    */
//  private val taskActorRef = MyFactory.actorOf(Props[Task], "task")

  def main(args: Array[String]): Unit = {
    //定义服务端的ip和端口
    val host = "127.0.0.1"
    val port = 8088
    /**
      * 使用ConfigFactory的parseString方法解析字符串,指定服务端IP和端口
      */
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$host
         |akka.remote.netty.tcp.port=$port
        """.stripMargin)
    /**
      * 将config对象传递给ActorSystem并起名为"server"，为了是创建服务端工厂对象(ServerActorSystem)。
      */
    val ServerActorSystem = ActorSystem("server", config)
    /**
      * 通过工厂对象创建服务端的ActorRef
      */
    val serverActorRef = ServerActorSystem.actorOf(Props[ActorWordCountServer], "actorServer")
    serverActorRef ! StartTask
  }

}
