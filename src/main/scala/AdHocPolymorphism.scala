import model.Human
import service.GenderService
import scala.reflect.runtime.universe._

object AdHocPolymorphism {
  def main (args: Array[String]) {
    val human = Human.valueOf("male").getOrElse(Human.Unknown())

    echo(human)
  }

  def echo[A <: Human](human: A)(implicit tag: TypeTag[A], wTag: WeakTypeTag[A], ev: scala.reflect.ClassTag[A]): Unit = {
    human match {
      case h: Human.Male    => doEcho[Human.Male](h)
      case h: Human.Female  => doEcho[Human.Female](h)
      case h: Human.Unknown => print("hoge")
    }
  }

  def doEcho[A <: Human](human: A)(implicit service: GenderService[A]): Unit = {
    service.printGender
  }
}


