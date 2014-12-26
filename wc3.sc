import spray.http.Uri
import spray.http.Uri.Query

//val base = Uri("http://google.co.jp/foo/bar?foo=bar")
val base = Uri("/foo/bar?foo=bar")
base.copy(query = ("hoge", "huga") +: base.query).toString
val scheme = base.scheme

