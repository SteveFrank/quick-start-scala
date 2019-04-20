package com.spark.demo.scala.project.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

import scala.concurrent.duration._


/**
  * @author yangqian
  * @date 2019-04-20
  */
class Master(val host: String, val port: Int) extends Actor {

  val idToWorkder = new collection.mutable.HashMap[String, WorkerInfo]()
  val workers = new mutable.HashSet[WorkerInfo]()

  // 超时检测间隔
  val CHECK_INTERVAL = 15000

  println("constructor invoked")

  /**
    * 准备连接
    */
  override def preStart(): Unit = {
    println("preStart invoked")
    import context.dispatcher
    context.system.scheduler.schedule(0 millis, CHECK_INTERVAL millis, self, CheckTimeoutWorker)
  }

  /**
    * 用于接受消息
 *
    * @return
    */
  override def receive: Receive = {
    case "hello" => {
      println("master hello check ... ...")
    }
    case RegisterWorker(id, memory, cores) => {
      /**
        * 1、保存Worker信息且不可重复
        * 判断是否注册成功
        */
      if (!idToWorkder.contains(id)) {
        // 将worker信息封装起来保存到内存中
        val workerInfo = new WorkerInfo(id, memory, cores)
        idToWorkder(id) = workerInfo
        workers += workerInfo
      }
      sender ! RegisteredWorker(s"akka.tcp://MasterSystem@$host:$port/user/Master")
    }
    case HeartBeat(id) => {
      if (idToWorkder.contains(id)) {
        val workerInfo = idToWorkder(id)
        // 报活
        val currentTime = System.currentTimeMillis()
        workerInfo.lastHeartBeatTime = currentTime
      }
    }
    case CheckTimeoutWorker => {
      // 删除不存活的节点
      val currentTime = System.currentTimeMillis()
      val toRemove = workers.filter(x => currentTime - x.lastHeartBeatTime > CHECK_INTERVAL)
      for (w <- toRemove) {
        workers.remove(w)
        idToWorkder -= w.id
      }

      println("当前存活节点: " + workers.size + ", 存活节点列表: " + workers.map(_.id))
    }
  }
}

object Master {
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt
    // 准备配置
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    // 创建ActorSystem老大，辅助创建和监控其中的Actor，单例
    val actorSystem = ActorSystem("MasterSystem", config)
    // 创建Actor actorOf -> 创建一个Actor实例
    val master = actorSystem.actorOf(Props(new Master(host, port)), "Master")
    master ! "hello"
    actorSystem.awaitTermination()
  }
}