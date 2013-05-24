import collection.mutable.Stack
import org.scalatest._

class StackSpec extends FlatSpec {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 3) // this will fail - should be 2
    assert(stack.pop() === 1)
  }

}