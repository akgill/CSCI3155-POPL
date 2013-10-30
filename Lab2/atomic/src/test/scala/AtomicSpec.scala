/******************************************************
 * This file contains the tests for the definitions in
 * Atomic.scala
 ******************************************************/

import org.scalatest.FlatSpec

class AtomicSpec extends FlatSpec {

  /* * * * * * * * * * * * *
   * Comprehensions tests
   * * * * * * * * * * * * */
  "yielding2 test, pg. 181, pp. 2 " should " pass " in {
    val theList = List(1, 2, 3, 5, 6, 7, 8, 10, 13, 14, 17)
    assert(Comprehensions.yielding2(theList)===List(1,3,5,7))
  }

  val activities = Vector(
    Comprehensions.Activity("01-01","Run"),
    Comprehensions.Activity("01-03","Ski"),
    Comprehensions.Activity("01-04","Run"),
    Comprehensions.Activity("01-10","Ski"),
    Comprehensions.Activity("01-03","Run"))

  "getDates test, pg. 181, pp. 5 " should " pass " in {
    assert(Comprehensions.getDates("Ski",activities)=== Vector("01-03", "01-10"))
    assert(Comprehensions.getDates("Run",activities)=== Vector("01-01", "01-04", "01-03"))
    assert(Comprehensions.getDates("Bike",activities)=== Vector())
  }

  "getActivities test, pg. 181, pp. 6 " should " pass " in {
    assert(Comprehensions.getActivities("01-01",activities)=== Vector("Run"))
    assert(Comprehensions.getActivities("01-02",activities)=== Vector())
    assert(Comprehensions.getActivities("01-03",activities)=== Vector("Ski","Run"))
    assert(Comprehensions.getActivities("01-04",activities)=== Vector("Run"))
    assert(Comprehensions.getActivities("01-10",activities)=== Vector("Ski"))
  }


  /* * * * * * * * * * * * *
   * Functions as Objects tests
   * * * * * * * * * * * * */
  "dogyears test, pg. 170, pp. 3 " should " pass " in {
    assert(FunObjs.dogYears(10)===70)
  }

  "between tests, pg. 171, pp. 6 " should " pass " in {
    assert(FunObjs.between(70, 80, 90)===false)
    assert(FunObjs.between(70, 60, 90)===true)
  }

  "squaring test, pg. 171, pp. 7 " should " pass " in {
    assert(FunObjs.give_s()==="1 4 25 9 49 ")
  }

  "pluralize tests, pg. 171, pp. 8 " should " pass " in {
    assert(FunObjs.pluralize("cat") === "cats")
    assert(FunObjs.pluralize("dog") === "dogs")
    assert(FunObjs.pluralize("silly") === "sillys")
  }

  /* * * * * * * * * * * * *
   * Map and Reduce tests
   * * * * * * * * * * * * */
  "modified simpleMap test, pg. 174, pp. 1 " should " pass " in {
    val v = Vector(1, 2, 3, 4)
    assert(MapReduce.simpleMap(v)===Vector(21, 32, 43, 54))
  }

  "modified simpleMap2 test, pg. 174, pp. 3 " should " pass " in {
    val v = Vector(1, 2, 3, 4)
    assert(MapReduce.simpleMap2(v)===Vector(21, 32, 43, 54))
  }

  "modified simpleMap test, pg. 174, pp. 4  " should " pass " in {
    val v = Vector(1, 2, 3, 4)
    assert(MapReduce.simpleMapwForLoop(v)===Vector(2, 3, 4, 5))
  }

  "modified simpleMap test, pg. 174, pp. 6  " should " pass " in {
    assert(MapReduce.sumIt(Vector(1, 2, 3)) === 6)
    assert(MapReduce.sumIt(Vector(45, 45, 45, 60)) === 195)
  }


  /* * * * * * * * * * * * *
   * Brevity tests
   * * * * * * * * * * * * */
  "test of assignResult with no intermediate \'result\' " should " pass " in {
    assert(Brevity.assignResult(true)===42)
    assert(Brevity.assignResult(false)===47)
  }
  
  
  "test of assignResult2 with no curly braces " should " pass " in {
    assert(Brevity.assignResult2(true)===42)
    assert(Brevity.assignResult2(false)===47)
  }

  "test of assignResult3 with no return type " should " pass " in {
    assert(Brevity.assignResult3(true)===42)
    assert(Brevity.assignResult3(false)===47)
  }


  /* * * * * * * * * * * * *
   * Zip tests
   * * * * * * * * * * * * */
  "specified test of zip problem with even number of people " should " pass " in {
    val people = Vector("Sally Smith", "Dan Jones", "Tom Brown",
      "Betsy Blanc", "Stormy Morgan", "Hal Goodsen")

    assert(Zip.zipper(people)===Vector(("Sally Smith","Betsy Blanc"),("Dan Jones","Stormy Morgan"),("Tom Brown","Hal Goodsen")))
  }

  "this tests behavior of Zip.zipper when given odd length Vector " should " be as expected " in {

    val people = Vector("Sally Smith", "Dan Jones", "Tom Brown",
      "Betsy Blanc", "Stormy Morgan", "Hal Goodsen", "Todd Anthony")

    assert(Zip.zipper(people)===Vector(("Sally Smith","Stormy Morgan"),("Dan Jones","Hal Goodsen"),("Tom Brown","Todd Anthony")))
  }

  "specified test of zip problem with List instead of Vector " should " pass " in {
    val people = List("Sally Smith", "Dan Jones", "Tom Brown",
      "Betsy Blanc", "Stormy Morgan", "Hal Goodsen")

    assert(Zip.zipper2(people)===Vector(("Sally Smith","Betsy Blanc"),("Dan Jones","Stormy Morgan"),("Tom Brown","Hal Goodsen")))
  }
}
