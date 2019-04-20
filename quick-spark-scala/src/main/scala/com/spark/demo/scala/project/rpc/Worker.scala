package com.spark.demo.scala.project.rpc

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

/**
  * @author yangqian
  * @date 2019-04-20
  */
class Worker(val masterHost: String, val masterPort: Int, val memory: Int, val cores: Int) extends Actor {

  var master : ActorSelection = _
  val workerId = UUID.randomUUID().toString
  val HEART_BEAT_INTERVAL = 10000

  /**
    * 建立连接
    */
  override def preStart(): Unit = {
    println("worker connect to master ... ...")
    /**
      * 需要有/user, Master要和master那边创建的名字保持一致
      * 与master之间建立连接
      */
    master = context.actorSelection(s"akka.tcp://MasterSystem@$masterHost:$masterPort/user/Master")

    /**
      * 向master发送消息
      * 注册消息包含ID信息
      */
    master ! RegisterWorker(workerId, memory, cores)
  }

  override def receive: Receive = {
    case RegisteredWorker(masterUrl) => {
      println(s"a reply from Master $masterUrl")
      // 定时发送心跳
      // 启动定时器 Akka
      import context.dispatcher
      val timerSchedule = context.system.scheduler
        .schedule(
          0 millis,
          HEART_BEAT_INTERVAL millis, self, SendHeartBeat)
    }
    case SendHeartBeat => {
      // TODO 业务逻辑 什么时候发送心跳消息
      master ! HeartBeat(workerId)
    }
  }

}

object Worker{
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt
    val masterHost = args(2)
    val masterPort = args(3).toInt

    val memory = args(4).toInt
    val cores  = args(5).toInt
    // 准备配置
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    //ActorSystem老大，辅助创建和监控下面的Actor，他是单例的
    val actorSystem = ActorSystem("WorkerSystem", config)
    actorSystem.actorOf(Props(new Worker(masterHost, masterPort, memory, cores)), "Worker")
    actorSystem.awaitTermination()
  }
}
