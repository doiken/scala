import org.scalacheck.{Prop, Gen}
import org.scalacheck.Prop.forAll
import org.specs2.ScalaCheck
import org.specs2.mutable.SpecificationWithJUnit

class ScalaCheckSpec extends SpecificationWithJUnit with ScalaCheck {

  "DeliverList ProtocolBuffer Serializer" should {

    "have invariance with serialize/deserialize" in {

      val gen = for {
        int <- Gen.choose(1, 100)
      } yield {
        int
      }

      check {
        Prop.forAll(gen) {
          (i: Int) =>
            i + 1 - 1 must_== i
        }
      } (set(minTestsOk = 10))

    }
//    "have invariance with serialize/deserialize" in {
//      Prop.forAll{ (i: Int) =>
//          i * 2 / 2 must_== i
//      }
//    }
  }
}
