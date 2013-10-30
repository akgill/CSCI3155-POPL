import scala.util.parsing.combinator._


object LetExpr {

  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Num(num: Double) extends Expr
  case class BinOp(op: String, left: Expr, right: Expr) extends Expr
  case class Let(name: String, value: Expr, body: Expr) extends Expr

  object ExprParser extends JavaTokenParsers {

    // Grammar based in part on this example in the Scala source code, specifically arithmeticParsers2
    // https://github.com/scala/scala-dist/blob/master/examples/src/main/scala/parsing/ArithmeticParsers.scala
    
    // Use `foldLeft` to convert concrete parse tree of binary ops to AST,
    // while maintaining left-right associativity
    //
    // The case expression here is already an anonymous (partial) function,
    // so we can directly assign to the name `reduceList`
    val reduceList: Expr ~ List[String ~ Expr] => Expr = {
      case lhs ~ rhs => rhs.foldLeft(lhs)(reduce) 
    }

    // Create a binop from an infix operator
    def reduce(left: Expr, right: String ~ Expr) = BinOp(right._1, left, right._2)

    def expr  : Parser[Expr] = letexpr | term ~ rep ("+" ~ term | "-" ~ term) ^^ reduceList
    def term  : Parser[Expr] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ reduceList
    def factor: Parser[Expr] = "(" ~> expr <~ ")" | num | variable ^^ {case (e:Expr) => e }
    def num: Parser[Expr] = floatingPointNumber ^^ {n => Num(n.toDouble)}
    def variable: Parser[Var] = ident ^^ { Var(_) }
    def let: Parser[String] = "let\\b".r
    def letexpr: Parser[Expr] = "let" ~ variable ~ "=" ~ expr ~ "in" ~ expr ^^ {
      case (_ ~ (x:Var) ~ _ ~ (value:Expr) ~ _ ~ (body:Expr)) => Let(x.name, value, body)
    }

    // NOTE: can select the desired parser combinator instead of
    // `expr` - might be useful for debugging
    def parseExpr(text: String) = parseAll(expr, text)
    def parse(text: String): Expr = parseExpr(text).get

  }

  def eval(env: Map[String, Double], e: Expr):Double = (e: @unchecked) match {
    // want to add cases in order of most basic to most complex, essentially 
    // following PEMDAS so the lowest level stuff is evaluated first
    // e.g. 1+5*3 will evaluate as 1 + (stuff)
    // (stuff) = 5 * 3
    // 5 is a number
    // 3 is a number
    case Num(n) => n
    case Var(x) => {
     env.get(x) match {
       case None => throw new NoSuchElementException
       case Some(d:Double) => d
     }
    }
    case BinOp("+", a, b) => eval(env, a) + eval(env, b)
    case BinOp("-", a, b) => eval(env, a) - eval(env, b)
    case BinOp("*", a, b) => eval(env, a) * eval(env, b)
    case BinOp("/", a, b) => eval(env, a) / eval(env, b)
    case Let(x, value, body) => { 
      val map = { env + (x -> eval(env, value)) }
      eval(map, body)
    }
  }

  // Helper function for top-level evaluation from source text and empty environment
  def evaluate(text: String): Double = eval(Map(), ExprParser.parse(text))
 
  def main(args: Array[String]) {
    val input = args.mkString(" ")
    println("Parsing: " + input)
    val ast = ExprParser.parseExpr(input)
    println("Parsed:  " + ast)
    println("Eval:    " + eval(Map(), ast.get))
  }

}
