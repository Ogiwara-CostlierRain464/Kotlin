package jp.ogiwara.kotlin.anitubeapi.method

import jp.ogiwara.kotlin.anitubeapi.Anitube
import jp.ogiwara.kotlin.anitubeapi.model.Video
import jp.ogiwara.kotlin.anitubeapi.http.getBody
import jp.ogiwara.kotlin.anitubeapi.makeVideo
import java.util.*

internal class SearchMethod(val keyword: String) {

    fun execute(): List<Video> {
        val result = ArrayList<Video>()
        val document = getBody(Anitube.URL.SEARCH + keyword)
        val elements = document.select(".mainBox ul .mainList")
        elements.forEach {
            result.add(makeVideo(it))
        }

        return result
    }
}