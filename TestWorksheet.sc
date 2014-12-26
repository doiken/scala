import scalaz._
import Scalaz._

//(List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _}
// * `(f1 |@| f2 |@| ... |@| fn)((v1, v2, ... vn) => ...)` is an alternative to `Apply[F].mapN(f1, f2, ..., fn)((v1, v2, ... vn) => ...)`
//Apply[List].apply10(List("ha", "heh", "hmm"), List("?", "!", "."))(_ + _)

val s1 = "hoge".successNel[String]
val s2 = "hoge".successNel[String]
val f1 = Failure("huga")
val f2 = Failure("huga")

//val a = Apply[ValidationNel[String, String]].apply4[String, String, String, String, String](s1, s2, f1, f2) (_ + _ + _ + _)
/*a match {
  case Success(a) => println(a)
  case Failure(a) => println(a)
}*/
(s1 |@| s2 |@| f1 |@| f2 ) (_ + _ + _ + _) match {
  case Success(a) => println(a)
  case Failure(a) => println(a)
}

//("hoge".successNel[String] |@| "huga".failureNel[String]) {
//  case Success(a) => println(a)
//  case Failure(e) => println(e)
//}

