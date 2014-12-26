object PartialFunc {
  def main(args: Array[String]): Unit = {
    val arg = "baz"
//    val pf1: PartialFunction[String, Unit] = { case "foo" => println("foo") }
//    val pf2: PartialFunction[String, Unit] = { case "bar" => println("bar") }
//    val pf3: PartialFunction[String, Unit] = { case "baz" => println("baz") }

//    val pf = pf1 orElse pf2 orElse pf3

//    if (pf.isDefinedAt(arg)) pf(arg)
    if (Impl.pf.isDefinedAt(arg)) Impl.pf(arg)
  }

}

/** stack pattern */
trait Base {
  def pf: PartialFunction[String, Unit]
}

trait Foo extends Base{
  abstract override def pf: PartialFunction[String, Unit] = super.pf.orElse(doRun1)

  def doRun1: PartialFunction[String, Unit] = { case "foo" => println("foo") }
}

trait Bar extends Base{
  abstract override def pf: PartialFunction[String, Unit] = super.pf.orElse(doRun2)

  def doRun2: PartialFunction[String, Unit] = { case "bar" => println("bar") }
}

trait Baz extends Base{
  abstract override def pf: PartialFunction[String, Unit] = super.pf.orElse(doRun3)

  def doRun3: PartialFunction[String, Unit] = { case "baz" => println("baz") }
}
trait Default extends Base {
  def pf: PartialFunction[String, Unit] = PartialFunction.empty[String, Unit]
}

object Impl extends Base with Default with Foo with Bar with Baz

/** Normal Pattern */
/*trait Base {
  def pf: PartialFunction[String, Unit] = PartialFunction.empty[String, Unit]
}

trait Foo extends Base{
  override def pf: PartialFunction[String, Unit] = super.pf.orElse(doRun1)

  def doRun1: PartialFunction[String, Unit] = { case "foo" => println("foo") }
}

trait Bar extends Base{
  override def pf: PartialFunction[String, Unit] = super.pf.orElse(doRun2)

  def doRun2: PartialFunction[String, Unit] = { case "bar" => println("bar") }
}

trait Baz extends Base{
  override def pf: PartialFunction[String, Unit] = super.pf.orElse(doRun3)

  def doRun3: PartialFunction[String, Unit] = { case "baz" => println("baz") }
}

object Impl extends Foo with Bar with Baz*/
