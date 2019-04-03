package com.spark.demo.scala.demo02

/**
  * @author yangqian
  * @date 2019-03-24
  */
object Demo {
  def main(args: Array[String]): Unit = {
    // 在耗费计算或者网络的时候回耗费较多资源
    lazy val a = 1
    println(a)

    // 比如类似读取文件这样的耗时操作
    import scala.io.Source._
    lazy val info = fromFile("/Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/src/main/com/scala/demo02/Demo.scala").mkString
    println(info)

  }
}
