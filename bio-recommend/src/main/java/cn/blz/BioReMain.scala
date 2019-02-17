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

    val userDF = sqlContext.sql("select * from r_user")
    val productDF = sqlContext.sql("select * from r_product")
    val ratingDF = sqlContext.sql("select * from r_rating")

    //ALS
    val splits = ratingDF.randomSplit(Array(0.8, 0.2), 0L)
    val trainingSet = splits(0).cache()
    val testSet = splits(1).cache()
    val model = new ALS().setRank(20).setMaxIter(10).setUserCol("userid").setItemCol("productid").setRatingCol("rating").fit(ratingDF)
    model.transform(testSet).write.insertInto("r_result")


    //    val recomForTopUser = model.recommendProducts(4169, 5)
    //    val movieTitle = movieDF.map(array => (array(0), array(1))).collectAsMap();
    //    val recomResult = recomForTopUser.map(rating => (movieTitle(rating.product), rating.rating)).foreach(println)

    //    val testUserProduct = testSet.map {
    //      case Rating(user, product, rating) => (user, product)
    //    }
    //    val testUserProductPredict = model.predict(testUserProduct)
    //    testUserProductPredict.take(10).mkString("\n")
    //
    //    val testSetPair = testSet.map {
    //      case Rating(user, product, rating) => ((user, product), rating)
    //    }
    //    val predictionsPair = testUserProductPredict.map {
    //      case Rating(user, product, rating) => ((user, product), rating)
    //    }

    //    val joinTestPredict = testSetPair.join(predictionsPair)
    //    val mae = joinTestPredict.map {
    //      case ((user, product), (ratingT, ratingP)) =>
    //        val err = ratingT - ratingP
    //        Math.abs(err)
    //    }.mean()
    //    //FP,ratingT<=1, ratingP>=4
    //    val fp = joinTestPredict.filter {
    //      case ((user, product), (ratingT, ratingP)) =>
    //        (ratingT <= 1 & ratingP >= 4)
    //    }
    //    fp.count()
    //
    //    import org.apache.spark.mllib.evaluation._
    //    val ratingTP = joinTestPredict.map {
    //      case ((user, product), (ratingT, ratingP)) =>
    //        (ratingP, ratingT)
    //    }
    //    val evalutor = new RegressionMetrics(ratingTP)
    //    evalutor.meanAbsoluteError
    //    evalutor.rootMeanSquaredError

    sc.stop()
  }

}
