Binding and scope Response
===

    {.scala .numberLines}
	val pi = 3.14
	def circumference(r: Double): Double = {
		val pi = 3.14159
		2.0 * pi * r 
	}
	def area(r: Double): Double =
		pi * r * r


* The use of `pi` at line #4 is bound at which line?

	_Ans: `pi` is bound by 3.14159 since there was a local variable with the name `pi` called in the scope of `def circumference`._

* The use of `pi` at line #7 is bound at which line?

	_Ans: Since there is no local variable for `pi`, the first declaration of `pi` outside of the scope of `def area` is used which happens to be 3.14._

---

	val x = 3
  	def f(x: Int): Int =
	 x match {
		 case 0 => 0
	 	case x =>
	 	val y = x + 1
        ({
           val x = y + 1
           y
         } * f(x - 1))
      }
    }
 	val y = x + f(x)

* The use of `x` at line 3 is bound at which line?

	_Ans:`x` is bound to the x parameter in the method `f`. This is how pattern matching is conventionally done too._

* The use of `x` at line 6 is bound at which line?

	_Ans: `x` is bound by the x parameter in the method `f`. This follows the same reasoning as above._

* The use of `x` at line 10 is bound at which line?

	_Ans: The curly braces serve as a limiter for where the scope of a variable applies. In this case, `x` is bound by the x parameter in the method `f`. It is easy to think that it might be bound by the x of `val x = y + 1` but this is not true. The reason why is that there is a curly brace before and after `val x = y + 1`, which is outside of `f(x - 1)`._ 

* The use of `x` at line 13 is bound at which line?
	
	_Ans: Now we are outside of the method `f`. Since `val x = 3` is the only available `x` we have outside of any scope, this is the x value that is used. If that `x` was not there, then the compiler will keep checking all other outer scopes until it finds and `x` value it can use. If no `x` is found, then an error occurs.

End of Binding and Scope Response
===
