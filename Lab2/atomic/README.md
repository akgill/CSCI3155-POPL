# Atomic Scala Problems Solution Descriptions
*(all tests done in AtomicSpec.scala)*
*(all definitions in Atomic.scala)*

### Comprehensions, pg. 181
**1.** Renamed `yielding` `onlyEvensUnderTen`.  Copying over this function just to rename it was not an enlightening exercise.

**2.** Modified `yielding` to create `yielding2` to accept and return a `List`. Satisfies test from book.

**5.** Made `Activity` case class as specified with a `getDates` method as specified, using a comprehension to act on each `Vector` element.

**6.** `getActivities` works exactly the same way as `getDates` but references a different argument.  There was nothing instructive about writing this function which seems an exercise in busy work in combination with question 5.

### Functions As Objects, pg. 170
**3.** Implemented as `val dogYears = (n:Int) => n*7`. A good problem for understanding the basics.

**6.** Implemented and tests pass.  Three arguments does seem a bit unwieldy for declaring functions as objects. A good extension of the first problem.

**7.** Filled in code as instructed with `x => (s = s + x*x + " ")`.

**8.** Again, did the assigned problem as instructed.  This problem is so similar to 7 that I question the instructive utility for the time cost.

### Map and Reduce, pg. 174
**1.** This trivial modification involved seeing if I could literally copy a function and change the mathematical operation in it correctly.  Again, what is the learning value of this for the time cost?

**3.** I accomplished this by using a comprehension. I do not know which approach may be more error prone between map and for.  My guess would be that this chapter favors maps and the intended solution does not use comprehensions which are introduced later.  This was a more useful problem because it involved independently solving something.

**4.** This problem is literally the same thing as number 3, but with the mathematical operation changed back to what it was changed from in exercise 1.Again, trivial, and yet still a mandatory obligation on my time.

**6.** This was a very useful problem as it is the only one assigned to use reduce.  Implemented it as `((v:Vector[Int]) => v.reduce((sum, n) => sum + n))`.

### Brevity, pg. 197
**1.** I am a monkey who can delete things exactly where told to delete them.

**2.** Again, deleting things as instructed.

**3.** Yep, still deleting things exactly as instructed in the book from a function I copied unmodified from the book.

**4.** Refactored `Coffee.scala` while trying to achieve the balance between brevity and clarity.

### Zip, pg. 305
**1.** I used the `take` and `takeRight` operations under `Vector` to segregate the people into groups and then zipped the two groups together.

**2.** When an odd number of people is used, the middle person in the list gets dropped because of rounding up in `takeRight` implementation.

**3.** When modifying the exercise to accept a `List`, the expected output must also be modified to be a `List`.


