import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

import scala.concurrent.Future

object SimpleTransform extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val sink: Sink[Any, Future[Done]] = Sink.foreach(println)
  val helloTimesTen: Flow[Int, String, NotUsed] = Flow[Int].map(i => s"Hello ${i * 10}")

  val graph: RunnableGraph[NotUsed] = source via helloTimesTen to sink
  graph.run()

  system.terminate()
}