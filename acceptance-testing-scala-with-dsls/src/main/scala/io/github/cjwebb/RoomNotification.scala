package io.github.cjwebb

sealed trait RoomNotification

case class Joined(to: UserId,
                  joiner: UserId,
                  joinerName: String,
                  roomId: RoomId) extends RoomNotification

case class AcceptedInvite(to: UserId,
                          acceptee: UserId,
                          accepteeName: String,
                          roomId: RoomId) extends RoomNotification