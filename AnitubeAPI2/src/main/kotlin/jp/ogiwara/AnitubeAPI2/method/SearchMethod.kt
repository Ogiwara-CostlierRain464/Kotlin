package jp.ogiwara.AnitubeAPI2.method

import jp.ogiwara.AnitubeAPI2.Anitube
import jp.ogiwara.AnitubeAPI2.http.getBody
import jp.ogiwara.AnitubeAPI2.makeVideo
import jp.ogiwara.AnitubeAPI2.model.Video

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