import akka.actor.ActorSystem
import akka.testkit.{ TestKit, ImplicitSender }
import org.specs2.matcher.Matchers
import org.specs2.mutable.Specification

/*
class MySpec(_system: ActorSystem) extends Specification with ImplicitSender {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Echo actor" should {
    "send back messages unchanged" in new TestKit(ActorSystem()) {
      val echo = system.actorOf(TestActors.echoActorProps)
      echo ! "hello world"
      expectMsg("hello world")
    }
  }
}*/
