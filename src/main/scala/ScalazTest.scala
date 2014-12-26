import scalaz._
import Scalaz._

object ScalazTest {
  def main (args: Array[String]) {
    // Kleisli
    val f = Kleisli { (x: Int) => (x + 1).some }
    val g = Kleisli { (x: Int) => (x * 100).some }
    val a = 4.some >>= (f >=> g)

    println(a)
  }

}


