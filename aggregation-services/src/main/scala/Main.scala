
import akka.actor.ActorSystem
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import play.api.libs.json._
import play.api.libs.json.extensions._

object Main extends App {

  implicit val timeout = 1 second
  implicit val actorSystem = ActorSystem()
  implicit val dispatcher = actorSystem.dispatcher

  val articleApi = new ArticleApi()
  val productApi = new ProductApi()

  def findProductIds(articleJson: JsValue): Seq[String] =
    (articleJson \\ "product_list")
      .flatMap(_.asOpt[List[String]])
      .flatten

  def fetchProducts(productIds: Seq[String]): Future[Map[String, JsValue]] = {
    Future.traverse(productIds){ id =>
      productApi.getProduct(id) map (pOpt => pOpt.map(p => (id, p)))
    } map (_.flatten.toMap)
  }

  def replaceProductIdsWithProducts(articleJson: JsValue,
                                    productMap: Map[String, JsValue]): JsValue = {
    def isProductList(jsPath: JsPath): Boolean =
      JsPathExtension.hasKey(jsPath) == Some("product_list")

    def replaceWithProducts(arr: Seq[JsValue]): Seq[JsValue] =
      arr.collect { case JsString(s) => productMap.get(s) }.flatten

    articleJson.updateAll {
      case (jsPath, JsArray(arr)) if isProductList(jsPath) =>
        JsArray(replaceWithProducts(arr))
    }
  }

  def transform(): Future[JsValue] = {
    for {
      article <- articleApi.getArticle("a1")
      productIds <- Future { findProductIds(article) }
      products <- fetchProducts(productIds)
    } yield {
      replaceProductIdsWithProducts(article, products)
    }
  }

  println(Await.result(transform(), 2 seconds))
  actorSystem.shutdown()
}