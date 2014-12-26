package model

// アドホック多相テストをしたい
sealed abstract class Human(val name: String) {}

object Human {
  case class Male()    extends Human(name = "male")
  case class Female()  extends Human(name = "female")
  case class Unknown() extends Human(name = "")

  val values: List[Human] = List(Male(), Female())
  def valueOf(name: String): Option[Human] = values.find(_.name == name)
}

