package com.spark.demo.scala.spark_streaming.utils

import org.apache.log4j.{Logger, Level}
import org.apache.spark.Logging


/**
  * @author yangqian
  * @date 2019-04-27
  */


object LoggerLevels extends Logging {

  def setStreamingLogLevels() {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }
}
