import jp.ogiwara.AnitubeAPI.Anitube
import jp.ogiwara.AnitubeAPI.model.Video


fun main(args:Array<String>){
    for(e in Anitube.search("Kill me baby")){
        println(e)
    }
}

//Rxとの互換性
//Anitube.topVideo
//Anitube.search
fun asyncTest(){

}

suspend fun zundoko(){
    val (z,d) = Pair("ズン","ドコ")
}

