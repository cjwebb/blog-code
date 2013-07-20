package io.github.cjwebb

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import spray.http.{HttpResponse, Uri, HttpRequest}
import spray.http.HttpMethods._
import akka.actor.Status.Success

import akka.testkit.TestActorRef
import akka.pattern.ask
import akka.actor.ActorSystem
import akka.util.Timeout
import java.util.concurrent.TimeUnit.SECONDS


class EchoServiceTest extends FreeSpec with ShouldMatchers with BeforeAndAfterAll {

  implicit val system = ActorSystem("testsystem")
  implicit val timeout = Timeout(1, SECONDS)

  "blah" in {
    val actorRef = TestActorRef(new EchoService)
    val future = actorRef ? HttpRequest(POST, Uri("/echo"))
    val Success(result: HttpResponse) = future.value.get
    result should be (42)
  }

  override def afterAll {
    system.shutdown()
  }
}
