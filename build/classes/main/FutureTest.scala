import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.Success

//import scalaz._
//import Scalaz._
import scala.concurrent.duration._

object FutureTest {
  def main (args: Array[String]) {
    import ExecutionContext.Implicits.global

/*    val f = Future{"hoge".some}
    f.andThen{
      case scala.util.Success(Some(str)) =>
        println(str)
    }
    val r = for {
      ff <- f
      if ff.isEmpty
    } yield ff.get

    r.onComplete {
      case scala.util.Success(a) =>
        println("foo")
        println(a)
        a
      case scala.util.Failure(e) =>
        println("goo")
        println(e)
        e
    }

    Await.ready(r, 1.seconds)*/

    val f2 = Future { 5 }.andThen{
      case Success(i:Int) => Future { Thread.sleep(2001); println(i * 2) }
    }.map(_ * 3)

    println(Await.result(f2, 1.seconds))
  }

}
