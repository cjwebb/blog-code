import play.api.libs.json.{Json, JsValue}
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global

class ProductApi {
  def getProduct(productId: String): Future[Option[JsValue]] = {
    Future {
      Sandwiches.sandwichMap.get(productId)
    }
  }
}

object Sandwiches {
  lazy val sandwichMap: Map[String, JsValue] = Map(
    "s1" -> clubSandwich,
    "s2" -> chickenAndBacon,
    "s3" -> blt
  ) mapValues (Json.parse(_))

  val clubSandwich =
    """
      |{
      |  "id": "s1",
      |  "name": "Club Sandwich",
      |  "image": "img/sandwich/s1.jpg"
      |}
    """.stripMargin

  val chickenAndBacon =
    """
      |{
      |  "id": "s2",
      |  "name": "Chicken & Bacon",
      | "image": "img/sandwich/s1.jpg"
      |}
    """.stripMargin

  val blt =
    """
      |{
      |  "id": "s3",
      |  "name": "BLT",
      |  "image": "img/sandwich/s1.jpg"
      |}
    """.stripMargin
}

