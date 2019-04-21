package com.spark.demo.scala.project.pro01

import scala.io.Source
import java.io.File

/**
  * @author yangqian
  * @date 2019-04-21
  */

object MyRichFile {
  implicit def fileToRichFile(f: File) = new RichFile(f)
}

class RichFile(val f: File) {
  def read() = Source.fromFile(f).mkString
}

object RichFile {
  def main(args: Array[String]): Unit = {
    try {
      val f = new File("/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/scala/words.txt")
      import MyRichFile._
      val contents = f.read()
      println(contents)
    } catch {
      case e: Exception => println(e)
    }
  }
}
