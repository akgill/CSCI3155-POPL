/*
 * This file contains the tests for the objects
 * in Warmup.scala
 */

import org.scalatest.FlatSpec

// contains tests for HealthMetrics object, calculateBMI tests
class HealthMetricsSpec extends FlatSpec {

  // tests for calculateBMI
  "For 160lbs, 68in, calculateBMI" should "say normal weight" in {
    assert(HealthMetrics.calculateBMI(160, 68) === ("Normal weight"))
  }

  "For 100lbs, 68in, calculateBMI" should "say underweight" in {
    assert(HealthMetrics.calculateBMI(100, 68) === ("Underweight"))
  }

  "For 200lbs, 68in, calculateBMI" should "say overweight" in {
    assert(HealthMetrics.calculateBMI(200, 68) === ("Overweight"))
  }
}

// contains tests for Word object, palindrome tests
class WordSpec extends FlatSpec {

  // tests for isPalindrome
  "For 'mom', isPalindrome" should "be true" in {
    assert(Word.isPalindrome("mom") === true)
  }  

  "For 'dad', isPalindrome" should "be true" in {
    assert(Word.isPalindrome("dad") === true)
  }

  "For 'street', isPalindrome" should "be false" in {
    assert(Word.isPalindrome("street") === false)
  }

  // tests isPalIgnoreCase
  "For 'Bob', isPalIgnoreCase" should "be true" in {
    assert(Word.isPalIgnoreCase("Bob") === true)
  }

  "For 'DAD', isPalIgnoreCase" should "be true" in {
    assert(Word.isPalIgnoreCase("DAD") === true)
  }

  "For 'Blob', isPalIgnoreCase" should "be false" in {
    assert(Word.isPalIgnoreCase("Blob") === false)
  }
}


// contains test for Truth object, Pattern Matching
class TruthSpec extends FlatSpec {

  val v = Vector(1)
  val v2 = Vector(3,4)

  // tests for oneOrTheOther
  "'oneOrTheOther(v == v.reverse)'" should "give 'True!'" in {
    assert(Truth.oneOrTheOther(v == v.reverse) === "True!")
  }

  "'oneOrTheOther(v2 == v2.reverse)'" should "give 'It's false'" in {
    assert(Truth.oneOrTheOther(v2 == v2.reverse) === "It's false")
  }
}


//contains test for Trip object, Pattern Matching with Case Classes
class TripSpec extends FlatSpec {

  val travelers = Vector(Trip.Passenger("Harvey","Rabbit"), 
    Trip.Passenger("Dorothy", "Gale"))
  val trip2 = Vector(Trip.Train(travelers, "Reading"),
    Trip.Plane(travelers, "B757"), Trip.Bus(travelers, 100))

  "travel(trip2(1))" should "say 'Plane B757 ' + 'Vector(Passenger(Harvey,Rabbit), Passenger(Dorothy,Gale))'" in {
      assert(Trip.travel(trip2(1)) === ("Plane B757 " + 
        "Vector(Passenger(Harvey,Rabbit), Passenger(Dorothy,Gale))"))
    }
}


// test for ListOps class's max method
class ListOpsSpec extends FlatSpec{

  val aList = List(10, 20, 45, 15, 30)

  "For aList = List(10, 20, 45, 15, 30), max(aList)" should "say 45" in {
    assert(ListOps.max(aList) === 45)
  }
}
