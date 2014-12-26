object Jad {
  def main (args: Array[String]): Unit = {
    println("hoge")
    println(hoge())
  }

  def hoge (foo: String = "aaa"): String = foo
}
