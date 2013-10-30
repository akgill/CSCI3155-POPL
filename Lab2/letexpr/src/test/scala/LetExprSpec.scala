import org.scalatest._
import LetExpr._

class LetExprSpec extends FlatSpec {

  "parser" should "correctly parse simple expressions" in {
    assert(ExprParser.parse("1+2") === BinOp("+", Num(1.0), Num(2.0)))
    assert(ExprParser.parse("1 + 2") === BinOp("+", Num(1.0), Num(2.0)))
    assert(ExprParser.parse("1 * 2") === BinOp("*", Num(1.0), Num(2.0)))
    // additional tests
    assert(ExprParser.parse("8 / 4") === BinOp("/", Num(8.0), Num(4.0)))
    assert(ExprParser.parse("2 - 1") === BinOp("-", Num(2.0), Num(1.0)))
    assert(ExprParser.parse("3*7") === BinOp("*", Num(3.0), Num(7.0)))
  }
  
  "parser" should "correctly parse left to right" in {
    assert(ExprParser.parse("1 * 2 * 3") === BinOp("*", BinOp("*", Num(1.0), Num(2.0)), Num(3.0)))
    assert(ExprParser.parse("1 - 2 - 3") === BinOp("-", BinOp("-", Num(1.0), Num(2.0)), Num(3.0)))

  // additional tests
    assert(ExprParser.parse("1 + 2 - 3") === BinOp("-", BinOp("+", Num(1.0), Num(2.0)), Num(3.0)))
    assert(ExprParser.parse("1 + 2 - 3 + 6 - 4") === BinOp("-",BinOp("+",BinOp("-",BinOp("+",Num(1.0),Num(2.0)),Num(3.0)),Num(6.0)),Num(4.0)))
    assert(ExprParser.parse("18 / 3 * 2") === BinOp("*",BinOp("/",Num(18.0),Num(3.0)),Num(2.0))) 
  }

  "parser" should "respect precedence" in {
    assert(ExprParser.parse("1 - 2 * 3") === BinOp("-", Num(1.0), BinOp("*", Num(2.0), Num(3.0))))
    // add additional tests
    assert(ExprParser.parse("1 * 4 - 3") === BinOp("-",BinOp("*",Num(1.0),Num(4.0)),Num(3.0))) 
    assert(ExprParser.parse("1 - 2 * 3 / 4") === BinOp("-",Num(1.0),BinOp("/",BinOp("*",Num(2.0),Num(3.0)),Num(4.0)))) 
    assert(ExprParser.parse("1 - 2 * 3 - 8 / 4") === BinOp("-",BinOp("-",Num(1.0),BinOp("*",Num(2.0),Num(3.0))),BinOp("/",Num(8.0),Num(4.0)))) 
  }

  "parser" should "correctly parse let expressions" in {
    assert(ExprParser.parse("let x = 1 in x") ===
           Let("x", Num(1.0), Var("x")))
    assert(ExprParser.parse("let foo = 42 in bar") ===
           Let("foo", Num(42.0), Var("bar")))
    assert(ExprParser.parse("let x = 1 in 2 * 3 * x") ===
           Let("x", Num(1.0), BinOp("*", BinOp("*", Num(2.0), Num(3.0)), Var("x"))))
    // add additional tests
    assert(ExprParser.parse("let blah = 6 in 2 * 1 * x + 5") ===
           Let("blah",Num(6.0),BinOp("+",BinOp("*",BinOp("*",Num(2.0),Num(1.0)),Var("x")),Num(5.0))))
    assert(ExprParser.parse("let v = 2 in 2 * v / 3") ===
           Let("v",Num(2.0),BinOp("/",BinOp("*",Num(2.0),Var("v")),Num(3.0))))
    assert(ExprParser.parse("let x = 1 in 6 / 3 - x") ===
           Let("x",Num(1.0),BinOp("-",BinOp("/",Num(6.0),Num(3.0)),Var("x"))))
  }

  "eval" should "correctly evaluate simple arithmetic in the AST" in {
    assert(eval(Map(), BinOp("*", BinOp("*", Num(1.0), Num(2.0)), Num(3.0))) === 6.0)
    assert(eval(Map(), BinOp("-", Num(1.0), BinOp("*", Num(2.0), Num(3.0)))) === -5.0)
    // add additional tests
    assert(eval(Map(), BinOp("-", BinOp("*",Num(2.0),Num(1.0)), BinOp("*", Num(2.0), Num(3.0)))) === -4.0)
    assert(eval(Map(), BinOp("/", Num(6.0), BinOp("*", Num(2.0), Num(3.0)))) === 1.0)
    assert(eval(Map(), BinOp("+", Num(1.0), BinOp("/", Num(9.0), Num(3.0)))) === 4.0)
  }

  "eval" should "correctly evaluate let expressions in the AST" in {
    assert(eval(Map(), Let("x", Num(1.0), Var("x"))) === 1.0)
    intercept[NoSuchElementException] {	    
      eval(Map(), Let("foo", Num(42.0), Var("bar")))
    }
    assert(eval(Map("bar" -> 42.0), Let("foo", Num(42.0), Var("bar"))) == 42.0)
    assert(eval(Map("foo" -> 42.0), Let("foo", Num(47.0), Var("foo"))) == 47.0)
    assert(eval(Map(), Let("x", Num(1.0), BinOp("*", BinOp("*", Num(2.0), Num(3.0)), Var("x")))) === 6.0)
    // add additional tests
    assert(eval(Map("bar" -> 15.0), Let("foo", Num(42.0), Var("bar"))) == 15.0)
    assert(eval(Map("foo" -> 42.0), Let("foo", Num(47.0), BinOp("-",Var("foo"),Num(10.0))))=== 37.0)
    assert(eval(Map(), Let("x", Num(1.0), BinOp("*", BinOp("/", Num(9.0), Num(3.0)), Var("x")))) === 3.0)
  }

  "evaluate" should "correctly parse and evaluate let expressions" in {
    assert(evaluate("let x = 1 in x") === 1.0)
    assert(evaluate("let x = 1 + 2 * 3 in 2 * x") === 14.0)
    assert(evaluate("(let x = 1 + 2 * 3 in 2 * x) * (let y = 2 in y * 2)") === 56.0)
    assert(evaluate("let x = 1 + 2 * 3 in x * (let x = 2 in x * 3 + 5) * x") === 7.0 * 11.0 * 7.0)
    // add additional tests
    assert(evaluate("(let x = 1 + 2 * 3 in 2 * x) / (let y = 1 in y * 7) + 1") === 3.0)
    assert(evaluate("let x = 1 + 2 * 3 in x * (let x = 2 in x * 3 + 5) * x - x * x") === 7.0 * 10.0 * 7.0)
    assert(evaluate("let x = 3 in x * (let x = 2 in x * 5) -  x") === 27.0)
  }

}
