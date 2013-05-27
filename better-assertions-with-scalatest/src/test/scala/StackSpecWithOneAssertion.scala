import collection.mutable._
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class StackSpecWithOneAssertion extends FlatSpec with ShouldMatchers {

    "A Stack" should "pop values in last-in-first-out order" in {
      val valuesToPush = Seq(1, 2, 3, 4)
      val stack = new Stack[Int]
      valuesToPush foreach { v => stack.push(v) }

      val valuesPopped = ArrayBuffer[Int]()
      while (stack.nonEmpty) valuesPopped += stack.pop()

      valuesPopped.update(0, 5) // to fail the test

      withClue("values popped from stack: ") { valuesPopped should be (valuesToPush.reverse) }
    }
}
