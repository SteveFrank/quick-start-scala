package com.spark.demo.scala.spark.demo

import org.apache.spark.sql.SparkSession

/**
  * @author yangqian
  * @date 2019-04-05
  */
object StudentDataFrameApiApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("StudentDataFrameApiApp")
      .master("local[2]")
      .getOrCreate()

    val rdd = spark.sparkContext
      .textFile("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/student.data")

    // 1|Burke|1-300-746-8446|ullamcorper.velit.in@ametnullaDonec.co.uk
    import spark.implicits._
    val studentDF = rdd
      .map(_.split("\\|"))
      .map(line => Student(line(0).toInt, line(1), line(2), line(3)))
      .toDF

    studentDF.printSchema()

    studentDF.filter("name = '' OR name = NULL").show()

    studentDF.createOrReplaceTempView("student")
    spark.sql("select * from student").show(30)

    val studentDF2 = rdd
      .map(_.split("\\|"))
      .map(line => Student(line(0).toInt, line(1), line(2), line(3)))
      .toDF

    studentDF.join(studentDF2, studentDF.col("id") === studentDF2.col("id")).show()


    spark.stop()
  }


  case class Student(id: Int, name: String, phone: String, email: String)

}
