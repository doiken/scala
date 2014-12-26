import scalaz._
import Scalaz._

/*type Stack = List[Int]
val pop = State[Stack, Int] {
  case x :: xs => (xs, x)
}
def push(a: Int) = State[Stack, Unit] {
  case xs => (a :: xs, ())
}
push(3).flatMap {
  a => pop(apply(a))
}
def stackManip: State[Stack, Int] = for {
  _ <- push(3)
  a <- pop
  b <- pop
} yield(b)

stackManip(List(5, 8, 2, 1))*/

import java.util.Random
def pickup() = State[List[Int], Option[Int]](list => (list.tail, list.headOption))

def TwoDice() = for {
  r1 <- pickup()
  r2 <- pickup()
} yield (r1, r2)

// start with a known seed
TwoDice().eval(List(1, 2, 3))
//TwoDice().eval(List(1, 2, 3))
