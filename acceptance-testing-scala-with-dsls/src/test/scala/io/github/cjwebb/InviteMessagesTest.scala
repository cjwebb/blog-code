package io.github.cjwebb

import invitedsl.{UserDSLImplicits, InviteMessagesDSL}
import org.scalatest.FreeSpec
import UserDSLImplicits._

class InviteMessagesTest extends FreeSpec with InviteMessagesDSL {

  "when someone joins a room, messages are sent to creator and inviter" in {
    Alice isFriendsWith Bob
    Alice isFriendsWith Charlie
    Bob isFriendsWith Charlie

    val room = Alice createsRoom ("Alice's Room")
    Bob joins room
    Bob invites Charlie to room

    Charlie joins room

    Alice notified Charlie hasJoined room
    Bob notified Charlie hasAcceptedInviteFor room
  }

}
