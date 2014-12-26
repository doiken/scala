package service

import model.Human

trait GenderService[A <: Human] {def printGender: Unit}

object GenderService {
  implicit val maleGenderService = new GenderService[Human.Male] {
    override def printGender: Unit = println("male")
  }

  implicit val femaleGenderService = new GenderService[Human.Female] {
    override def printGender: Unit = println("female")
  }
}


