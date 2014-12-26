object ImplicitTest {
  def main(args: Array[String]): Unit = {
    println(Super.sub1.name)
  }
}

trait Super[A] {val name: String}
object Super {
  implicit val sub1 = new Super[String] { val name = "sub1"}
}
