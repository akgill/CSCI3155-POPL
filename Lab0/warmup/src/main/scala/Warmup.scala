/* 
 * This file contains the definitions that will
 * be tested in WarmupSpec.scala
 */

// class container for calculateBMI
object HealthMetrics {

  // method in HealthMetrics to calculate BMI
  def calculateBMI(lbs: Double, height: Double) = {
    val bmi = lbs/(height*height) * 703.07
    if (bmi < 18.5) "Underweight"
    else if (bmi < 25) "Normal weight"
    else "Overweight"
  }
}

// class container for palindrome tests
object Word {

  // method that reports if a word is a palindrome
  def isPalindrome(word: String) = {
    (word == word.reverse)
  }

  // case insensitive palindrome checking
  def isPalIgnoreCase(word: String) = {
    (word.toLowerCase == word.toLowerCase.reverse)
  }
}

// class container for Pattern Matching exercise
object Truth {

  // method that returns "True!" if true, "It's false" otherwise
  def oneOrTheOther(bool: Boolean) = {
    bool match {
      case true => "True!"
      case _    => "It's false"
    }
  }
}

// PatternMatchingCaseClasses.scala from pg.187-188 of
// Atomic Scala, with addiitonal Plane class
object Trip {
  case class Passenger(first: String, last: String)
  case class Train(travelers: Vector[Passenger], line: String)
  case class Bus(passengers: Vector[Passenger], capacity: Int)
  case class Plane(passengers: Vector[Passenger], name: String)

  def travel(transport: Any):String = {
    transport match {
      case Train(travelers, line) => s"Train line $line $travelers"
      case Bus(travelers, seats) => s"Bus size $seats $travelers"
      case Plane(travelers, name) => s"Plane $name $travelers"
      case Passenger => "Walking Along"
      case what => s"$what is in limbo!"
    }
  }
}


// Object containing a method to find the max of a list
// without using List's max method.  Uses pattern matching.
object ListOps {
  // removes the smallest element of the first two in the list
  // at each recursive step until only one 
  // element remains.
  def max(list: List[Int]): Int = {
    if (list.tail.isEmpty) 
      list.head
    else if (list(0) > list(1)) 
      max(list(0) :: list.tail.tail)
    else 
      max(list.tail)
  }
}
