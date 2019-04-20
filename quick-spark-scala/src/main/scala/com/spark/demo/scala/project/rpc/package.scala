package com.spark.demo.scala.project

/**
  * @author yangqian
  * @date 2019-04-20
  */
package object rpc {

  /**
    * worker -> master 发送注册信息
    *
    * @param id
    * @param memory
    * @param cores
    */
  case class RegisterWorker(id: String, memory: Int, cores: Int) extends RemoteMessage

  /**
    * master -> worker
    *
    * @param masterUrl
    */
  case class RegisteredWorker(masterUrl: String) extends RemoteMessage

  /**
    *
    * worker -> self
    * worker 进程内消息
    */
  case object SendHeartBeat {

    // 业务处理

  }

  /**
    *
    * Master -> self
    * master 进程内消息
    */
  case object CheckTimeoutWorker {

    // 业务处理
  }

  case class HeartBeat(id: String)
}
