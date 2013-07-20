package io.github.cjwebb

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{OneInstancePerTest, FreeSpec}

class FriendsGraphSpec extends FreeSpec with ShouldMatchers with OneInstancePerTest {
  val graph = new FriendsGraph()

  "can store and retrieve people" in {
    val alice = Person("alice-id", "Alice")
    graph.putPerson(alice)
    graph.getPerson(alice.id) should be (Some(alice))
  }

  "can store a friend" in {
    val alice = Person("alice-id", "Alice")
    val bob = Person("bob-id", "Bob")

    graph.putPerson(alice)
    graph.putPerson(bob)

    graph.makeFriends(alice, bob)

    graph.getFriends(alice.id) should be (List(bob))
  }

  "retrieves friends, first in last out" in {
    val alice = Person("alice-id", "Alice")
    val bob = Person("bob-id", "Bob")
    val charlie = Person("charlie-id", "Charlie")

    graph.putPerson(alice)
    graph.putPerson(bob)
    graph.putPerson(charlie)

    graph.makeFriends(alice, bob)
    graph.makeFriends(alice, charlie)

    graph.getFriends(alice.id) should be (List(charlie, bob))
  }
}
