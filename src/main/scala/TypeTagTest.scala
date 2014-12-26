import model.Human
import service.GenderService

import scala.reflect.runtime.universe._

/**
 * 型タグとマニフェスト: http://docs.scala-lang.org/ja/overviews/reflection/typetags-manifests.html
 * What is TypedTag: http://stackoverflow.com/questions/12218641/scala-what-is-a-typetag-and-how-do-i-use-it
 */
object TypeTagTest {
  def main (args: Array[String]) {
    val human = Human.valueOf("male").getOrElse(Human.Unknown())


//    echo(human)
    human match {
      case h: Human.Male    => echo[Human.Male](h)
      case h: Human.Female  => echo[Human.Female](h)
      case h: Human.Unknown => echo[Human.Unknown](h)
    }
  }

  def echo[A <: Human](human: A)(implicit tag: TypeTag[A], wTag: WeakTypeTag[A], ev: scala.reflect.ClassTag[A]): Unit = {
    println(tag.tpe)
    println(wTag.tpe.resultType)
    println(ev)
//    val a = ev.runtimeClass.asInstanceOf[]
//    doEcho[ev.type](human.asInstanceOf[ev.type])

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


