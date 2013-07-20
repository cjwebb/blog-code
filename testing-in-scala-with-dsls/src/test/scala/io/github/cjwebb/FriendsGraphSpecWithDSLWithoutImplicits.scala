package io.github.cjwebb

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{OneInstancePerTest, FreeSpec}

class FriendsGraphSpecWithDSLWithoutImplicits extends FreeSpec with ShouldMatchers with OneInstancePerTest {
  val graph = new FriendsGraph()

  "can store and retrieve people" in {
    val alice = Person("alice-id", "Alice")

    graph.putPerson(alice)

    graph.getPerson(alice.id) should be (Some(alice))
  }

  "can store a friend" in {
    val (alice, bob) = (_alice, _bob)

    alice befriends bob

    alice isFriendsWith bob
  }

  "retrieves friends, first in last out" in {
    val (alice, bob, charlie) = (_alice, _bob, _charlie)

    alice befriends bob
    alice befriends charlie

    alice isFriendsWith (charlie, bob)
  }

  def _alice() = createPerson("alice-id", "Alice")
  def _bob() = createPerson("bob-id", "Bob")
  def _charlie() = createPerson("charlie-id", "Charlie")

  def createPerson(id: String, name: String) = {
    val person = Person(id, name)
    graph.putPerson(person)
    PersonDSL(person)
  }

  case class PersonDSL(person: Person) {
    def befriends(friend: PersonDSL) = {
      graph.makeFriends(person, friend.person)
    }
    def isFriendsWith(friends: PersonDSL*) = {
      graph.getFriends(person.id) should be (friends.map(_.person))
    }
  }
}
