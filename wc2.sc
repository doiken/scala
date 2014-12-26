import scalaz._
import Scalaz._

trait DcAdxMessageBuilder {
//  def build[A <: Any]: Option[A] // <: ResponseMessage
  def echo: Unit = println("a")
}

case class DcAdxResponseMaterial (
  startTimeMs: Int,
  ad: Option[DcAdxResponseAdMaterial] = None
) extends DcAdxMessageBuilder {

}
case class DcAdxResponseAdMaterial(
  htmlSnippet: String,
  buyerCreativeId: Int,
  vendorType: Int,
  clickThroughUrl: String,
  adSlot: Option[DcAdxResponseAdSlotMaterial] = None
) extends DcAdxMessageBuilder {}

case class DcAdxResponseAdSlotMaterial (
  id: String,
  maxCpm: Long
) extends DcAdxMessageBuilder {}

val r = DcAdxResponseMaterial(1)

val adSlot = DcAdxResponseAdSlotMaterial("ID", 1000000l)
val ad = DcAdxResponseAdMaterial("SNIPPET", 10000, 1, "CLICK URL", adSlot.some)

val result = r.copy(ad = ad.some)
