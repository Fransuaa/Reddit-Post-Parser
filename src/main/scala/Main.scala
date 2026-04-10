object Main {

  type Subscription = (String, String)  // (subredditName, url)


  def main(args: Array[String]): Unit = { 
    val header = s"Reddit Post Parser\n${"=" * 40}"

    // EJ. 1
    val subscriptions: Option[List[Subscription]] = FileIO.readSubscriptions()

    subscriptions match {
      case Some(s) => println(s)
      case None => println("ERROR: No se pudo extraer subscripciones ;(")
    }
  }

  //   val allPosts: List[Subscription] = subscriptions.map { url =>
  //     println(s"Fetching posts from: $url")
  //     val posts = FileIO.downloadFeed(url)
  //     (url, posts)
  //   }

  //   val output = allPosts
  //     .map { case (url, posts) => Formatters.formatSubscription(url, posts) }
  //     .mkString("\n")

  //   println(output)
}