package io.github.cjwebb.invitedsl

import io.github.cjwebb.UserId

trait InviteMessagesDSL {
  val Alice = User(UserId("alice-id"), "Alice")
  val Bob = User(UserId("bob-id"), "Bob")
  val Charlie = User(UserId("charlie-id"), "Charlie")
}
