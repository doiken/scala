/*case class Cell() {
  // TがInt型の場合にのみ呼び出せる
  def increment[A](v: A)(implicit ev:A =:= Int ): Int = v + 1
}*/
// TがDateなのでformatDateを呼べる
//Cell( new java.util.Date).formatDate
// java.sql.TimestampはDateを継承するのでformatDateを呼べる
//Cell( new java.sql.Timestamp( System.currentTimeMillis)).formatDate
// TがDateではないのでformatDateは呼べない
//Cell("123").formatDate
case class Test() {
  def increment[A](v: A)(implicit ev: A =:= Foo ): Hoge = v
}
sealed class Hoge(val i: Int) {}
case class Foo(override val i: Int) extends Hoge(i: Int)
case class Bar(override val i: Int) extends Hoge(i: Int)
case class Baz(override val i: Int) extends Hoge(i: Int)

case class Factory() {
  def piyo(i: Int): Hoge = {
    Foo(i)
  }
}
Test().increment(Factory().piyo(1).asInstanceOf[Foo]).i
