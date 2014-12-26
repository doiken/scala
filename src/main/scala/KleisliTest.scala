import scalaz.Scalaz._
import scalaz._

object KleisliTest {
  def main (args: Array[String]) {
    // Kleisli=(a -> m b)
    val f = Kleisli { (x: Int) => (x + 1).some }
    val g = Kleisli { (x: Int) => (x * 100).some }

    val a1 = (f >=> g).run(4)
    val a2 = 4.some >>= (f >=> g)

    println(a1)
    println(a2)
  }

}


