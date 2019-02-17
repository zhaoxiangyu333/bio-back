package cn.blz

import kafka.serializer.StringDecoder
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object logToHive {
  val topics = "biotopic"
  val brokes = "192.168.80.142:6667"

  def main(args: Array[String]): Unit = {
    val sf = new SparkConf().setAppName("logToHive")
    val sc = new SparkContext(sf)
    val sqlContext = new HiveContext(sc)
    val ssc = new StreamingContext(sc, Seconds(5))

    sqlContext.sql("use biorecommend")

    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokes)
    val topicsSet = topics.split(",").toSet
    val message = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicsSet)

    message.foreachRDD(rdd => {
      import sqlContext.implicits._

      val line = rdd.map(_._2).filter(_.indexOf("add") != -1).map(_.split(" ")(2))

      line.map(x => BUser(x.split("-")(1).toInt)).toDF().write.insertInto("r_user")
      line.map(x => BProduct(x.split("-")(2).toInt, x.split("-")(3).toInt)).toDF().write.insertInto("r_product")
      line.map(x => BRating(x.split("-")(1).toInt, x.split("-")(2).toInt, 1)).toDF().write.insertInto("r_rating")
    })

    ssc.start()
    ssc.awaitTermination()
    sc.stop()
  }
}
