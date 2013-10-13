object Lab1 {
  
  /*
   * CSCI 3155: Lab 1
   */

  /*
   * Replace the 'throw new UnsupportedOperationException' expression with
   * your code in each function.
   * 
   * Do not make other modifications to this template, such as
   * - adding "extends App" or "extends Application" to your Lab object,
   * - adding a "main" method, and
   * - leaving any failing asserts.
   * 
   * Your lab will not be graded if it does not compile.
   * 
   * This template compiles without error. Before you submit comment out any
   * code that does not compile or causes a failing assert.  Simply put in a
   * 'throws new UnsupportedOperationException' as needed to get something
   * that compiles without error.
   */
  
  /* Exercise 3a */
  // Completed. No errors occur in the FlatSpec test.

  def abs(n: Double): Double = {if(n < 0) return -n; else n;}

  /* Exercise 3b */
  // I edited this down to 4 lines (Amandeep). 

  def xor(a: Boolean, b: Boolean): Boolean = {
    if(a){
      if(b) return false
      else return true
    }else return b 
  }

  /* Exercise 4a */
  // Completed. No errors occur in the FlatSpec test.

  def repeat(s: String, n: Int): String = {
    if(n == 0) return ""
    if(n < 0) throw new IllegalArgumentException
    if (n == 1) return s
    else s + repeat(s, n - 1)
  }
  
  /* Exercise 4b */
  
  def sqrtStep(c: Double, xn: Double): Double = xn - (((xn*xn) - c) / (2*xn))
  
  def sqrtN(c: Double, x0: Double, n: Int): Double = throw new UnsupportedOperationException
  
  def sqrtErr(c: Double, x0: Double, epsilon: Double): Double = {
    require(c >=0)
    val x1 = sqrtStep(c, x0)
    if (abs(x1*x1-c) < epsilon){
      return x1
    } else return sqrtErr(c, x1, epsilon)
  }
  
  def sqrt(c: Double): Double = sqrtErr(c, 1.0, 0.0001)
  
  /* Exercise 5 */
  
  sealed abstract class SearchTree
  case object Empty extends SearchTree
  case class Node(l: SearchTree, d: Int, r: SearchTree) extends SearchTree
  
  /* checks that current node greater than specified min, less than
   * or equal to specified max, and then checks that left/right branches
   * are either empty or between min and current node (for left branch)
   * or between current node and max (for right branch)
   */
  def repOk(t: SearchTree): Boolean = {
    def check(t: SearchTree, min: Int, max: Int): Boolean = t match {
      case Empty => return true
      case Node(l, d, r) => {
        if (d >= min && d < max && check(l,min,d) && check(r,d,max)) true
        else false
      }
    }
    check(t, Int.MinValue, Int.MaxValue)
  }
  
  /* Finds the correct place to insert a number into the tree by
   * searching down correct branches of tree until an empty node 
   * is found. The number is inserted there as a new Node with Empty
   * branches.
   */
  def insert(t: SearchTree, n: Int): SearchTree = t match {
    case Empty => new Node(Empty,n,Empty)
    case Node(l, d, r) => {
      if (n >= d) new Node(l, d, insert(r,n))
      else new Node(insert(l,n), d, r)
    }
  }
  
  /* Simply returns the tree with leftmost node removed, and the
   * value in that node.
   */
  def deleteMin(t: SearchTree): (SearchTree, Int) = {
    require(t != Empty)
    (t: @unchecked) match {
      case Node(Empty, d, r) => (r, d)
      case Node(l, d, r) =>
        val (l1, m) = deleteMin(l)
        (new Node(l1, d, r), m)
    }
  }

  /* This function searches for a value n in the tree t and return
   * the tree with n removed.  If n is not found, it returns t unchanged.
   */
  def delete(t: SearchTree, n: Int): SearchTree = t match {
    case Empty => t               // if Empty tree, return t 
    case Node(Empty, d, Empty) => {   // if one element tree,
      if (n == d) Empty               // return Empty if n==d
      else t  }                       // t otherwise
    case Node(l, d, r) => {       // if not at bottom of tree
      if (n == d){                    // if n is found 
        if (r == Empty) l             // if r is Empty, return left tree
        val (newr, newd) = deleteMin(r) // if r not Empty, node with lowest
        new Node(l, newd, newr) } // node in r at root, children below
      else if (n < d) new Node(delete(l,n), d, r) // recurse to find n == d
      else new Node(l, d, delete(r,n)) }
  }

  
}
