import jp.ogiwara.AnitubeAPI2.Anitube
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking {
    val job = launch(CommonPool){
        val videos = Anitube.searchAsync("kill me baby").await()
        println("Found ${videos.size} Videos.")
        println(videos.first().getMp4UrlAsync().await())
    }
    job.join()
}