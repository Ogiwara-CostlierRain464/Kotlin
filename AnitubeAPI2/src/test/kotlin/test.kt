import jp.ogiwara.AnitubeAPI2.Anitube
import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) = runBlocking {
    val job = launch(CommonPool){
        println(Anitube.search("kill me baby").await())
    }
    job.join()
}

suspend fun aha(){
    println()
}
