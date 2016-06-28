import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

import scala.concurrent.Future

object UsingASink extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val iterable = 1 to 100

  val source: Source[Int, NotUsed] = Source(iterable)
  val sink: Sink[Any, Future[Done]] = Sink.foreach(println)

  source.runWith(sink)

  system.terminate()
}