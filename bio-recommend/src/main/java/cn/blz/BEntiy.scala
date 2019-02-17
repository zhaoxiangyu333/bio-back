package cn.blz

class BEntiy(){
  def parseBUser(str: String): BUser = {
    val lines = str.split("-")
    BUser(lines(1).toInt)
  }

  def parseBProduct(str: String): BProduct = {
    val lines = str.split("-")
    BProduct(lines(2).toInt, lines(3).toInt)
  }

  def parseBRating(str: String): BRating = {
    val lines = str.split("-")
    BRating(lines(1).toInt, lines(2).toInt, 1)
  }
}

case class BUser(userId: Int)

case class BProduct(productId: Int, categoryId: Int)

case class BRating(userId: Int, productId: Int, Rating: Int)
