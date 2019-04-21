package com.spark.demo.scala

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

/**
  * @author yangqian
  * @date 2019-04-21
  */
package object spark_sql {

  case class Person(id: Long, name: String, age: Int)

  val personStructType = StructType {
    List(
      StructField("id",   IntegerType,  true),
      StructField("name", StringType,   true),
      StructField("age",  IntegerType,  true)
    )

  }

}
