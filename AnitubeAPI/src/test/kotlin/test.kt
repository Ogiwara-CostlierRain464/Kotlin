import jp.ogiwara.AnitubeAPI.Anitube
import jp.ogiwara.AnitubeAPI.model.Video


fun main(args:Array<String>){
    val client = Anitube()
    for(e in client.search("Kill me baby")){
        println(e)
    }
}