package util

trait Convert[A] { val str: String}

object Convert {
  implicit def int2convert(i: Int) = new Convert[Int] {
    val str = (i * 2).toString
  }

  implicit def long2convert(l: Long) = new Convert[Long] {
    val str = (l * 3).toString
  }
}

