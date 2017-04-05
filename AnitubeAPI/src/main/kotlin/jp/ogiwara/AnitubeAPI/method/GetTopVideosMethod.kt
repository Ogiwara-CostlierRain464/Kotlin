package jp.ogiwara.AnitubeAPI.method

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.javanet.NetHttpTransport
import jp.ogiwara.AnitubeAPI.ANITUBE_URL_TOP
import jp.ogiwara.AnitubeAPI.model.Video
import jp.ogiwara.AnitubeAPI.utils.trim
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException

/**
 * Created by ogiwara on 2017/04/04.
 *
 * @param fragment
 */
class GetTopVideosMethod(val fragment: String) {

    object FRAGMENT{
        val HIGHLIGHT = "fragment-1"
        val TOP_RATED = "fragment-2"
        val MOST_SEEN = "fragment-3"
    }

    fun execute(): ArrayList<Video>{
        val result = ArrayList<Video>()
        val httpTransport = NetHttpTransport()
        try {
            val requestFactory = httpTransport.createRequestFactory()
            val url = GenericUrl(ANITUBE_URL_TOP)
            val req = requestFactory.buildGetRequest(url)
            val res = req.execute()
            try{
                val html = res.parseAsString()
                val document = Jsoup.parse(html)
                val element = document.getElementById(fragment)

                val document1 = Jsoup.parse(element.outerHtml())
                val elements = document1.select("#$fragment ul .mainList")
                for(e: Element in elements)
                    result.add(makeVideoModel(e))

            }finally {
                res.disconnect()
            }
        }catch (e: IOException){
            e.printStackTrace()
        }finally {
            httpTransport.shutdown()
        }
        return result
    }

    fun makeVideoModel(e: Element): Video{
        val document = Jsoup.parse(e.outerHtml())
        val element = document.select(".mainList .videoThumb")
        val e2 = element.first()
        val html = e2.outerHtml()
        val videoUrl = html.trim("a href=\"","\" title")
        //val title = html.trim("title=\"","\">")//要件等
        val imgUrl = html.trim("<img src=\"","\" alt")

        val element2 = document.select(".mainList .videoInfo .videoViews")
        val e3 = element2.first()
        val html2 = e3.outerHtml()
        val views = html2.trim("videoViews\">\n","Visualiza")

        val element3 = document.select(".mainList .videoTitle")
        val e4 = element3.first()
        val html3 = e4.outerHtml()
        val title = html3.trim("$videoUrl\">","</a>")

        return Video(title,views,imgUrl,videoUrl)
    }
}