import org.scalatest._
import Lab1._

class Problem3Spec extends FlatSpec {

  // Check that abs works for negative, positive and zero values

  "abs" should "evaluate to the absolute value of the argument" in {
     assert(abs(12) == 12)
     assert(abs(-12) == 12)
  }

  // Check that xor works for all four combinations of boolean values

  "xor" should "evaluate to the exclusive or of the arguments" in {
     assert(xor(false, false) == false)
     assert(xor(true, false) == true)
     assert(xor(false, true) == true)
     assert(xor(true, true) == false)
  } 
}
