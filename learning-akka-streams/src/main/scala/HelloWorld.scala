import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

object HelloWorld extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val iterable = 1 to 100
  val source: Source[Int, NotUsed] = Source(iterable)

  source.runForeach(println)

  system.terminate()
}