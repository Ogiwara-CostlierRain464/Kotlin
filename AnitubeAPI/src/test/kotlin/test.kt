import jp.ogiwara.AnitubeAPI.Anitube
import jp.ogiwara.AnitubeAPI.model.Video


fun main(args:Array<String>){
    val client = Anitube()
    for(v: Video in client.search("kill me baby")){
        println(v.lowMp4Url)
    }
}