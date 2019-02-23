package cn.blz

import org.apache.spark.ml.recommendation.ALS
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

object BioReMain {
  def main(args: Array[String]): Unit = {
    val sf = new SparkConf().setAppName("BioReMain")
    val sc = new SparkContext(sf)
    val sqlContext = new HiveContext(sc)

    sqlContext.sql("use biorecommend")
    val ratingDF = sqlContext.sql("select * from r_rating")

    //ALS
    val splits = ratingDF.randomSplit(Array(0.8, 0.2), 0L)
    val trainingSet = splits(0).cache()
    val testSet = splits(1).cache()
    val model = new ALS().setRank(20).setMaxIter(10)
      .setUserCol("userid").setItemCol("productid").setRatingCol("rating")
      .fit(trainingSet)
    model.transform(testSet).write.insertInto("r_result")


    sc.stop()
  }
}
