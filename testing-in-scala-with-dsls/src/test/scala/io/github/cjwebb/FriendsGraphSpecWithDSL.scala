package io.github.cjwebb

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{OneInstancePerTest, FreeSpec}

class FriendsGraphSpecWithDSL extends FreeSpec with ShouldMatchers with OneInstancePerTest {
  val graph = new FriendsGraph()

  "can store and retrieve people" in {
    val alice = _alice

    getPerson(alice) should be (alice)
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
    person
  }

  def getPerson(person: Person) = {
    graph.getPerson(person.id).getOrElse(fail("could not find person: " + person))
  }

  implicit def pimpPerson(person: Person): PersonDSL = PersonDSL(person)

  case class PersonDSL(person: Person) {
    def befriends(friend: PersonDSL) {
      graph.makeFriends(person, friend.person)
    }
    def isFriendsWith(friends: PersonDSL*) {
      graph.getFriends(person.id) should be (friends.map(_.person))
    }
  }
}

