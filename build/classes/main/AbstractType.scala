import model.Human
import service.GenderService

object AbstractType {
  def main(args: Array[String]): Unit = {
    Sub1.echo
    Sub1.doEcho(Sub1.gender)
  }
}

trait Super {
  type Gender <: Human
  val gender: Gender
  def echo {println(s"gender is ... ${gender.name}")}

  def doEcho(gender: Gender)(implicit service: GenderService[Gender]): Unit = {
    service.printGender
  }
}

object Sub1 extends Super {
  override type Gender = Human.Male
  override val gender: Sub1.Gender = Human.Male()
}
