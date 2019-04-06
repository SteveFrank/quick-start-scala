package com.spark.demo.scala.spark

import org.apache.spark.sql.SparkSession

/**
  * @author yangqian
  * @date 2019-04-05
  */
object DataFrameApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DataFrameApp")
      .master("local[2]")
      .getOrCreate()

    // 将json文件加载成为一个DataFrame
    val peopleDF = spark.read.format("json").load("file:///Users/qian/WorkSpaces/idea-workspace/scala-workspace/quick-start-scala/quick-spark-scala/src/main/resources/spark/people.json")

    // 输出对应DataFrame对应的schema信息
    peopleDF.printSchema()

    // 输出数据集中的前20条数据
    peopleDF.show()

    // 方式一
    peopleDF.select("name").show()

    // 方式二
    // 查询某几列所有的数据，并对于列进行计算 select name from table
    peopleDF.select(peopleDF.col("name"), (peopleDF.col("age") + 10).as("ageAddTen")).show()

    // 方式三 过滤操作 select * from table where age > 19
    peopleDF.filter(peopleDF.col("age") > 19).show()

    // 根据某一列进行分组 然后再进行聚合操作
    // select age, count(1) from table group by age
    peopleDF.groupBy("age").count().show()

    spark.stop()
  }

}
