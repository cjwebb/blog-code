import play.api.libs.json.{Json, JsValue}
import scala.concurrent.{Future, ExecutionContext}
import ExecutionContext.Implicits.global

class ArticleApi {
  def getArticle(articleId: String): Future[JsValue] = {
    Future {
      Json.parse(Articles.a1)
    }
  }
}

object Articles {
  val a1 = """
      |{
      |    "id": "a1",
      |    "title": "Sandwich",
      |    "content": "Sandwiches are tasty...",
      |    "product_list": [
      |        "s1",
      |        "s2",
      |        "s3"
      |  ]
      |}
    """.stripMargin
}
