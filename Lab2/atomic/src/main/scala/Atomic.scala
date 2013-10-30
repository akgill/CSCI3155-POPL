/*********************************************************
 * This file contains the definitions that will be tested
 * in AtomicSpec.scala
 *********************************************************/ 


/* * * * * * * * * * * * * * * * * 
 * Comprehensions, pg. 181 Atomic Scala, problems 1, 2, 5, 6
 * * * * * * * * * * * * * * * * */ 

object Comprehensions {
  // 1. renamed yielding
  def onlyEvensUnderTen(v:Vector[Int]):Vector[Int]={
    val result = for {
      n <- v
      if n < 10
      if n % 2 != 0
    } yield n
    result
  }


  // 2. yielding 2 modified to accept a List and return a List
  def yielding2(v:List[Int]):List[Int] = {
    for {
      n <- v
      if n < 10
      isOdd = (n % 2 != 0)
      if(isOdd)
    } yield n
  }

  // 5. case class Activity
  case class Activity(date:String, act:String)

  // method getDates returns all dates an activity was done
  def getDates(act:String, v:Vector[Activity]):Vector[String] = {
    //v.foreach(n => if n.act==act
    //  n.date)
    for {
      n <- v
      if n.act equals(act)
    } yield n.date
  }

  // 6. getActivities returns all of the activities done on a certain day`
  def getActivities(date:String, v:Vector[Activity]):Vector[String] = {
    for {
      n <- v
      if n.date equals(date)
    } yield n.act
  }
}

/* * * * * * * * * * * * * * * * * 
 * Functions as Objects, pg. 170 Atomic Scala, problems 3, 6, 7, 8 
 * * * * * * * * * * * * * * * * */

object FunObjs {
  // 3. anonymous function reporting dogyears, test in AtomicSpec.scala
  val dogYears = (n:Int) => n*7

  // 6. anonymous function that says if temperate between high and low
  val between = (temperate:Int, low:Int, high:Int) => (temperate<= high && temperate >=low)

  // 7. anonymous function that squares a list of numbers
  var s=""
  val numbers = Vector(1, 2, 5, 3, 7)
  numbers.foreach(x => (s = s + x*x + " "))

  // this is for the purpose of testing in AtomicSpec.scala
  def give_s() = s

  // 8. an anonymous function that adds "s" to a word, test in AtomicSpec.scala
  val pluralize = ((x:String) => x+"s")
}

  /* * * * * * * * * * * * * * * * * 
   * Map and Reduce, pg. 174 Atomic Scala, problems 1, 3, 4, 6
   * * * * * * * * * * * * * * * * */

object MapReduce {
  // 1. modified SimpleMap.scala
  val simpleMap = ((v:Vector[Int]) => v.map(n => n*11+10))

  // 3. rewriting exercise 1 result using for
  val simpleMap2 = ((v:Vector[Int]) => for{ n <- v} yield n*11+10)

  // 4. rewriting SimpleMap.scala using a for loop
  val simpleMapwForLoop = ((v:Vector[Int]) => for{ n <- v} yield n+1)

  // 6. wrote sumIt function using reduce
  val sumIt = ((v:Vector[Int]) => v.reduce((sum, n) => sum + n))
}

  /* * * * * * * * * * * * * * * * * 
   * Brevity, pg. 197 Atomic Scala, problems 1-4
   * * * * * * * * * * * * * * * * */
 
  object Brevity {

    // 1. rewritten assignResult with no intermediate result
    def assignResult(arg:Boolean):Int = {
      if(arg) 42 else 47
    }

    // 2. rewritten assignResult with no curly braces
    def assignResult2(arg:Boolean):Int = if(arg) 42 else 47

    // 3. rewritten assignResult2 with no return type
    def assignResult3(arg:Boolean) = if(arg) 42 else 47

    // 4. refactored Coffee.scala from Constructors
    class Coffee(val shots:Int = 2,val decaf:Boolean = false,
                 val milk:Boolean = false,val toGo:Boolean = false,
                 val syrup:String = "") {
      var result = ""
      println(shots, decaf, milk, toGo, syrup)
      def getCup() = {
        if(toGo) result += "ToGoCup " 
        else result += "HereCup "
      }
      def pourShots() = {
        for(s <-0 until shots) 
          if(decaf) result += "decaf shot " 
          else result +="shot "
        }
      def addMilk() = {if(milk) result += "milk "}
      def addSyrup() = {result += syrup}

      getCup()
      pourShots()
      addMilk()
      addSyrup()
    }
 }
  /* * * * * * * * * * * * * * * * * 
   * Zip, pg. 305 Atomic Scala, problems 1-3
   * * * * * * * * * * * * * * * * */
  object Zip {
   
    // 1. zip code to pair people as in problem
    // 2. tested with both even and odd length Vectors in AtomicSpec.scala
    def zipper(people:Vector[String]):Vector[(String,String)] = {
      val group1 = people.take(people.size/2)
      val group2 = people.takeRight(people.size/2)
      val pairs = group1 zip group2
      pairs
    }

    // 3. to use a List instead of a Vector
    def zipper2(people:List[String]):List[(String,String)] = {
      val group1 = people.take(people.size/2)
      val group2 = people.takeRight(people.size/2)
      val pairs = group1 zip group2
      pairs
    }
  }
