import java.lang.RuntimeException

import akka.actor.{Props, Actor}
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import sbt.testing.Status
import spray.routing.directives.BasicDirectives
import spray.util.LoggingContext
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import spray.routing._
import spray.http._
import MediaTypes._

import scalaz._
import Scalaz._

import scala.util.control.NonFatal

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
//  implicit val eh: ExceptionHandler = MyExceptionHandler.default
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  import scala.concurrent.ExecutionContext.Implicits.global
  val testActor1 = actorRefFactory.actorOf(Props[TestActor1], "TestActor1")

  implicit val timeout: Timeout = Timeout(10 seconds)
  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            val result = for {
              r <- (testActor1 ? "MyService").mapTo[Throwable \/ String]
//              r <- Future("hoge").mapTo[String]
//              _ <- Future{parameters('a).sequence}.flatMap{Future{println("a")}}
            } yield r

            val response = Await.result(result, 1 seconds)
            response.leftMap(throw _)
            <html>
              <body>
                <h1>{response}</h1>
              </body>
            </html>
          }
        }
      }
    }
}

class TestActor1 extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global
  val testActor2 = context.system.actorOf(Props[TestActor2], "TestActor2")

  def receive = {
    case str: String =>
      if (str == "MyService") {
        val result = for {
          r <- (testActor2 ? s"TestActor1 -> $str")(1 seconds).mapTo[Throwable \/ String]
        } yield r
        result pipeTo sender
      } else {
        sender ! "else"
      }
  }
}

class TestActor2 extends Actor {
  def receive = {
    case str: String =>
      sender ! s"TestActor2 -> $str".right
  }
}

/*object MyExceptionHandler {
  import StatusCodes._
  type PF = PartialFunction[Throwable, Route]

  implicit def apply(pf: PF): ExceptionHandler =
    new ExceptionHandler {
      def isDefinedAt(error: Throwable) = pf.isDefinedAt(error)
      def apply(error: Throwable) = pf(error)
    }

  implicit def default(implicit settings: RoutingSettings, log: LoggingContext): ExceptionHandler =
    apply {
      case e: IllegalRequestException ⇒ ctx ⇒
        log.warning("Illegal request {}\n\t{}\n\tCompleting with '{}' response",
          ctx.request, e.getMessage, e.status)
        ctx.complete(e.status, e.info.format(settings.verboseErrorMessages))

      case e: RequestProcessingException ⇒ ctx ⇒
        log.warning("Request {} could not be handled normally\n\t{}\n\tCompleting with '{}' response",
          ctx.request, e.getMessage, e.status)
        ctx.complete(e.status, e.info.format(settings.verboseErrorMessages))

      case e: RuntimeException ⇒ ctx ⇒
        log.error(e, "HogeHoge PiyoPiyo {}", ctx.request)
        ctx.complete(BadGateway)
      case NonFatal(e) ⇒ ctx ⇒
        log.error(e, "Error during processing of request {}", ctx.request)
        ctx.complete(InternalServerError)
    }
}*/
