package cn.blz

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object logToHive {
  val topics = "biotopic"
  val brokes = "192.168.80.142:6667"

  def main(args: Array[String]): Unit = {
    //    val conf = new SparkConf().setAppName("logToHive")
    //    val sc = new SparkContext(conf)
    //    val sqlContext = new HiveContext(sc)

    val spark = SparkSession.builder()
      .appName("logToHive")
      .master("local[2]")
      .enableHiveSupport() //开启支持hive
      .getOrCreate()

    val ssc = new StreamingContext(spark.sparkContext, Seconds(5))


    import spark.sql
    import spark.implicits._
    sql("use biorecommend")

    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokes)
    val topicsSet = topics.split(",").toSet
    val message = KafkaUtils.createDirectStream[String, String](
      ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams))

    message.foreachRDD(rdd => {

      val line = rdd.map(_.value()).filter(_.indexOf("add") != -1).map(_.split(" ")(2))

      line.map(x => BRating(x.split("-")(1).toInt, x.split("-")(2).toInt, 1)).toDF().write.insertInto("r_rating")
    })

    ssc.start()
    ssc.awaitTermination()
    //    sc.stop()
    spark.stop()
  }
}
