case class Product(id: String, name: String)

object UsingOptions {
  val productMap = Map ("id_1" -> Product("id_1", "Product 1"))

  def getProduct(id: String): Option[Product] = {
    val productOption = productMap.get("id_1")
    ???
  }
}
