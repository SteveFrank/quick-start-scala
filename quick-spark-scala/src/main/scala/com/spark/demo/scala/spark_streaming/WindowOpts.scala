package com.spark.demo.scala.spark_streaming
import com.spark.demo.scala.spark_streaming.utils.LoggerLevels
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}

/**
  * @author yangqian
  * @date 2019-04-27
  */


/**
  * Created by ZX on 2016/4/19.
  */
object WindowOpts {

  def main(args: Array[String]) {
    LoggerLevels.setStreamingLogLevels()
    val conf = new SparkConf().setAppName("WindowOpts").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Milliseconds(5000))
    val lines = ssc.socketTextStream("localhost", 9999)
    val pairs = lines.flatMap(_.split(" ")).map((_, 1))
    val windowedWordCounts = pairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(15), Seconds(10))
    //Map((hello, 5), (jerry, 2), (kitty, 3))
    windowedWordCounts.print()
    //    val a = windowedWordCounts.map(_._2).reduce(_+_)
    //    a.foreachRDD(rdd => {
    //      println(rdd.take(0))
    //    })
    //    a.print()
    //    //windowedWordCounts.map(t => (t._1, t._2.toDouble / a.toD))
    //    windowedWordCounts.print()
    //    //result.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
