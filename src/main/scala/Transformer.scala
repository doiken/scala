import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Await, Future}
import scalaz._
import Scalaz._

object Transformer {
  def main (args: Array[String]): Unit = {
/*    //@see https://gist.github.com/krasserm/1082242
    val v: ListT[Option, Int] = ListT[Option, Int](Some(List(1, 2, 3)))

    println(v.map(_ * 2))

    val c1: EitherT[Option, String, Int] = EitherT[Option, String, Int](Some(\/-(1)))
    println(c1.map(_ * 3).run)*/

    //@see https://gist.github.com/krasserm/1082242

    // ListT
    def times: Option[List[Int]] = Some(List(4, 5))
    val l1: Option[List[Int]] = Some(List(1, 2, 3))
    val lt = {for {
      i: Int       <- ListT.fromList(l1)
      j: List[Int] <- times.liftM[ListT] // LiftMはListにList[Int]を放り込む
      if i == 0
    } yield {
      i * j.head
    }}.run
    println(lt)

    // EitherT
    val c1: Option[String \/ Int] = Some(\/-(1))
    val et = {for {
      i <- EitherT.eitherT(c1)
    } yield {
      i * 3
    }}.run
//    println(et)

    // OptionT
    val listOpts: List[Option[Int]] = List(Some(1), Some(2), Some(3))
    val ot = {for {
      i <- OptionT.optionT(listOpts)
    } yield i * 2}.run
    //    println(ot)

/*    implicit val ec = ExecutionContext.global
    val futureOpt: Future[Option[Int]] = Future{Some(1)}
    implicit val functor = Functor[Future]
    val fo = {for {
      // Functor[Future] => FutureInstance (implicit def to CoBind)
      i <- OptionT.optionT(futureOpt)
    } yield i * 2}.run
    println(Await.result(fo, Duration.Inf))*/

  }
}
