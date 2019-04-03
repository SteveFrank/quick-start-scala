package com.spark.demo.scala.chapter09

import java.io.PrintWriter

import scala.io.Source

/**
  * @author yangqian
  * @date 2018-12-10
  */
class FileDemo {

}

object FileDemo {

  def main(args: Array[String]): Unit = {

    val source = Source.fromFile(
      "/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/src/main/com/scala/chapter09/myfile.txt"
      , "UTF-8")

//    val iter = source.buffered
//
//    while (iter.hasNext) {
//      print(iter.next())
//    }

//    val contents = source.mkString
//    println(contents)

    val out = new PrintWriter("/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/src/main/com/scala/chapter09/myfile.txt")
    for (i <- 1 to 100) out.println(i)

    out.close()
    source.close()

//    val lineIterator = source.getLines()
//    for (l <- lineIterator) {
//      println(l)
//    }

//    println("===========")
//    val  lines = source.getLines().toArray
//    for  (i <- 1 until lines.length) {
//      println(lines.apply(i))
//    }

//    for (l <- lines) {
//      println(l)
//    }
  }

}
