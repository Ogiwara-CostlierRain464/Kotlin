package jp.ogiwara.AnitubeAPI.method

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import jp.ogiwara.AnitubeAPI.ANITUBE_URL_SEARCH
import jp.ogiwara.AnitubeAPI.model.Video
import jp.ogiwara.AnitubeAPI.utils.trim
import org.jetbrains.annotations.TestOnly
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException


class SearchMethod(val query: String) {

    fun execute(): ArrayList<Video> {
        val result = ArrayList<Video>()
        val httpTransport = NetHttpTransport()
        try{
            val requestFactory = httpTransport.createRequestFactory()
            val url = GenericUrl(ANITUBE_URL_SEARCH + query)
            val req = requestFactory.buildGetRequest(url)
            val res = req.execute()
            try{
                val html = res.parseAsString()
                val document = Jsoup.parse(html)
                val elements = document.select(".mainBox ul .mainList")
                for(e: Element in elements) {
                    result.add(makeVideoTest(e))
                    //makeVideoTest(e)
                }
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

    fun makeVideoTest(e: Element): Video{
        val thumb = e.select(".videoThumb").first()
        val videoUrl = thumb.getElementsByTag("a").first().attr("href")
        val imgUrl = thumb.getElementsByTag("a").first().getElementsByTag("img").first().attr("src")
        val views = e.select(".videoInfo .videoViews").first().ownText().replace(" Visualizaçoes","")
        val title = e.select(".videoTitle").first().getElementsByTag("a").first().ownText()
        println(e.select(".videoTitle").first())
        return Video(title,views,imgUrl,videoUrl)
    }

    fun makeVideoModel(e: Element): Video{
        val document = Jsoup.parse(e.outerHtml())
        val element = document.select(".mainList .videoThumb")
        val e2 = element.first()
        val html = e2.outerHtml()
        //println(e2.getElementsByTag("a").first().getElementsByTag("href"))
        /*for(ek in e2.getElementsByTag("a")){
            println(ek.attr("href"))
            println()
        }*/


        val videoUrl = html.trim("a href=\"","\" title")
        //val title = html.trim("title=\"","\">")//要件等
        val imgUrl = html.trim("<img src=\"","\" id")//要件等
        /**
         * 検索の場合は、idが先に来る…
         * Jsonで正式にparse?->無理そう
         */

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