package io.github.cjwebb.invitedsl

import io.github.cjwebb.{RoomId, UserId}

case class User(id: UserId, name: String)
case class Room(id: RoomId, name: String)

class UserDSL(user: User) {
  def isFriendsWith(user: User) = ???
  def createsRoom(roomName: String): Room = ???
  def joins(room: Room) = ???
  def invites(invitee: User) = new UserInviteDSL(user, invitee)
  def notified(sender: User) = new UserNotifyDSL(user, sender)
}

class UserInviteDSL(inviter: User, invitee: User) {
  def to(room: Room) = ???
}

class UserNotifyDSL(recipient: User, sender: User) {
  def hasJoined(room: Room) = ???
  def hasAcceptedInviteFor(room: Room) = ???
}

object UserDSLImplicits {
  implicit def userToUserDSL(user: User) = new UserDSL(user)
}