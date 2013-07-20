package io.github.cjwebb

import akka.actor.{Props, ActorSystem, ActorRef, Actor}
import spray.http.{Uri, HttpResponse, HttpRequest}
import spray.http.HttpMethods._
import akka.io.IO
import spray.can.Http

class EchoService extends Actor {

  def receive = {
    case _: Http.Connected => sender ! Http.Register(self)
    case HttpRequest(POST, Uri.Path("/echo"), _, entity, _) => {
      sender ! HttpResponse(entity = entity)
    }
  }
}

object Boot extends App {
  implicit val system = ActorSystem()

  val myListener: ActorRef = system.actorOf(Props[EchoService])

  IO(Http) ! Http.Bind(myListener, interface = "localhost", port = 8080)
}