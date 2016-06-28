import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}

import scala.concurrent.{ExecutionContext, Future}

object SendToDB extends App {

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val intSource: Source[Int, NotUsed] = Source(1 to 100)

  val helloTimesTen: Flow[Int, String, NotUsed] = Flow[Int].map(i => s"Hello ${i * 10}")
  val intToEvent: Flow[Int, DB.Event, NotUsed] = Flow[Int].map(i => DB.Event(s"Event $i"))

  val printlnSink: Sink[Any, Future[Done]] = Sink.foreach(println)
  val dbSink = Flow[DB.Event].map(DB.persistEvent).toMat(Sink.ignore)(Keep.right).named("dbSink")

  val graph = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
    import GraphDSL.Implicits._
    val broadcast = builder.add(Broadcast[Int](2))

    intSource ~> broadcast ~> helloTimesTen ~> printlnSink
                 broadcast ~> intToEvent ~> dbSink

    ClosedShape
  })

  graph.run()

  system.terminate()
}

object DB {
  case class Event(msg: String)
  def persistEvent(e: Event)(implicit ec: ExecutionContext): Future[Unit] = {
    // pretend that some DB IO happens here
    println(s"persisting $e")
    Future {}
  }
}
