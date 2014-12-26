import scalaz._
import Scalaz._

object Transformer {
  def main (args: Array[String]): Unit = {
    //@see https://gist.github.com/krasserm/1082242
    val v: ListT[Option, Int] = ListT[Option, Int](Some(List(1, 2, 3)))
    println(v.map(_ * 2))

    val c1: EitherT[Option, String, Int] = EitherT[Option, String, Int](Some(\/-(1)))
    println(c1.map(_ * 3).run)

  }
}
