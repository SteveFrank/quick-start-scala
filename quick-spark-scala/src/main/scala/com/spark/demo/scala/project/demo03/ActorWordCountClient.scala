package com.spark.demo.scala.project.demo03

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import com.spark.demo.scala.project.ManageObjects._

/**
  * @author yangqian
  * @date 2019-04-20
  */

class ActorWordCountClient(host: String, port: Int) extends Actor {
  var serverActorRef: ActorSelection = _ // 服务端的代理对象

  // 在receive方法之前调用
  override def preStart(): Unit = {
    // akka.tcp://Server@127.0.0.1:8088
    serverActorRef = context.actorSelection(s"akka.tcp://Server@$host:$port/user/actorServer")
  }

  override def receive: Receive = {
    case StartTask =>
      println("ActorWordCountClient start")

    case filename: String =>
      val path = serverActorRef.pathString
      println(s"server path : $path")
      serverActorRef ! SubmitTask(filename)
      println(s"ActorWordCountClient send filename to server : $filename")

    case ResultTask(map) =>
      println(s"receive result Map:$map")

    case _ =>
      println("nothing receive from server")
  }
}


object ActorWordCountClient {

  def main(args: Array[String]): Unit = {
    //指定客户端的IP和端口
    val host = "127.0.0.1"
    val port  = 8089

    //指定服务端的IP和端口
    val serverHost = "127.0.0.1"
    val serverPort = 8088

    /**
      * 使用ConfigFactory的parseString方法解析字符串,指定客户端IP和端口
      */
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$host
         |akka.remote.netty.tcp.port=$port
        """.stripMargin)

    /**
      * 将config对象传递给ActorSystem并起名为"Server"，为了是创建客户端工厂对象(clientActorSystem)。
      */
    val clientActorSystem = ActorSystem("client", config)

    // 创建dispatch | mailbox
    val clientActorRef = clientActorSystem.actorOf(Props(new ActorWordCountClient(serverHost, serverPort.toInt)), "ActorClient")

    clientActorRef ! StartTask // 自己给自己发送了一条消息 到自己的mailbox => receive

    val files = Array(
      "/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/scala/words.txt",
      "/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/scala/words.txt"
    )
    for (f <- files) {
      clientActorRef ! f
      Thread.sleep(1000)
    }
  }

}
