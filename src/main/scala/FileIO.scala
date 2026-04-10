import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io.Source

object FileIO {

  implicit val formats: Formats = DefaultFormats  // lo requiere json4s

  // Impure (IO) function to read subscriptions from a JSON file
  def readSubscriptions(): Option[List[Main.Subscription]] = {
    try {
      val path = "./subscriptions.json"
      val source = Source.fromFile(path)  // abro stream hacia subscriptions.json (similar a un file descriptor)

      // extraigo contenido del archivo de manera segura
      val json = try {
        source.mkString   // extraigo contenido
      } finally {
        source.close()  // cierro stream (evito leaks)
      }

      val content = parse(json)  // parseo json (string) a JValue (estructura de arbol)
      val subs = content.children.map { item =>
        val name = (item \ "name").extract[String] // indico donde acceder y que tipo extraer
        val url = (item \ "url" ).extract[String]
        (name, url)
      }
      Some(subs)

    // si falla (cualquier excepcion) devuelvo None
    } catch {
      case _: Exception => None
    }
  }

  // Pure function to download JSON feed from a URL
  def downloadFeed(url: String): String = {
    val source = Source.fromURL(url)
    source.mkString
  }
}
