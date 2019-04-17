package com.spark.demo.scala.project.demo01

/**
  * @author yangqian
  * @date 2019-04-15
  */
object HelloScala {

  val func = (x: Int) => x * 10

//  def m1(f: Int => Int): Int = {
////    val f0 = f
////    f0(3)
//    f(3)
//  }

  val func2:(Int, Double) => (Double, Int) = {
    (a, b) => (b, a)
  }


  def me1(func: Int => Int) = {
    func(3)
  }

  def main(args: Array[String]) = {
    // yield  生成一个新的集合
    // Vector 矢量数组
    val v1 = for (i <- 1 to 10) yield i * 10
    println(v1)

    val v2 = for (i <- 1.to(10)) yield i * 10
    println(v2)

    // 定义方法 def
    def m1(a: Int, b: Int) = a * b

    m1(2, 3)

    // 定义函数 函数（函数的参数列表）, 返回值
    val a = (x: Int, y: Int) => {
      x + y
    }
    a(1, 2)

    // 函数可以作为值传入方法里面
    val f1 = (x: Int) => x * 10
    val f2 = (x: Int) => x + 10

    val r = 1 to 10
    r.map(f1)
    r.map(f2)

    val c = me1(func)
    print(c)

    val arr1 = Array(1,2,3,4,5,6,7,8,9,0)

    val r1 = arr1.map(x => x * 5)
    val r2 = arr1.map(x => x - 1)
    println(r1.toBuffer)
    println(r2.toBuffer)

  }

}
