import scalaz._
import Scalaz._

object FunctorApplicativeMonad {
  def main(args: Array[String]): Unit = {
    functor
    monad
  }

  def functor: Unit = {
    // fmap(fa: F[A])(f: A => B): F[B]
    val res = Functor[Option].map(Option(1))(_ + 1)
    println(res)
  }

  def applicative: Unit = {
    // def <*>[A, B](fa: F[A])(f: F[A] => F[B]): F[B]

    val res = Monad[Option].map(Some(1))(_ + 1)
    println(res)
  }
  def monad: Unit = {
    // def map[A, B](fa: F[A])(f: A => F[B]): F[B]
    // def point[A](a: => A): F[A]

    val res = Monad[Option].map(Some(1))(_ + 1)
    println(res)
  }

}
