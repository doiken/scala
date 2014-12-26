import scalaz._
import Scalaz._

type EitherTList = EitherT[List, String, Int]

val v = EitherTList.point(1)
val w = EitherT.right(2)

val r = for {
  a <- v
  b <- w
} yield a + b

r
/*val result = ListT(Option(List(1,2,3)))
result.map(_+45)
val r: ListT[Option, String] = for {
  value <- result
} yield value.toString

r*/

