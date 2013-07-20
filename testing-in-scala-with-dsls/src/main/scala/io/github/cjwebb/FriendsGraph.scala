package io.github.cjwebb

import scala.collection.concurrent.TrieMap

case class Person(id: String, name: String)

/**
 * In-Memory version of a Friend's graph.
 * Not thread safe.
 */
class FriendsGraph {

  private val people = new TrieMap[String, Person]
  private val friends = new TrieMap[String, List[Person]]

  def putPerson(person: Person) {
    people.put(person.id, person)
  }

  def getPerson(id: String): Option[Person] = {
    people.get(id)
  }

  def makeFriends(personA: Person, personB: Person) {
    def insertFriend(person: Person, friend: Person) {
      friends.get(person.id) match {
        case Some(friendList) => friends.update(person.id, friend :: friendList)
        case None             => friends.put(person.id, friend :: Nil)
      }
    }
    insertFriend(personA, personB)
    insertFriend(personB, personA)
  }

  def getFriends(id: String): List[Person] = {
    friends.get(id).getOrElse(List.empty)
  }
}
