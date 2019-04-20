package com.spark.demo.scala.project.rpc

/**
  * @author yangqian
  * @date 2019-04-20
  */
class WorkerInfo(val id: String, val memory: Int, val cores: Int) extends RemoteMessage {
  // TODO 上一次心跳
  var lastHeartBeatTime: Long = _
}
