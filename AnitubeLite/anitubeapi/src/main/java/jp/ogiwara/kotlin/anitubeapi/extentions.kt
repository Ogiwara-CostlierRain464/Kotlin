package jp.ogiwara.kotlin.anitubeapi

import jp.ogiwara.kotlin.anitubeapi.model.Video
import org.apache.commons.lang3.StringUtils
import org.jsoup.nodes.Element

fun String.trim(from: String,to: String): String{
    val str1 = StringUtils.substringAfter(this,from)
    val str2 = StringUtils.substringBefore(str1,to)
    return str2
}

fun makeVideo(e: Element): Video {
    val thumb = e.select(".videoThumb").first()
    val videoUrl = thumb.getElementsByTag("a").first().attr("href")
    val imgUrl = thumb.getElementsByTag("a").first().getElementsByTag("img").first().attr("src")
    val views = e.select(".videoInfo .videoViews").first().ownText().replace(" Visualizaçoes","")
    val title = e.select(".videoTitle").first().getElementsByTag("a").first().ownText()
    return Video(title,views,imgUrl,videoUrl)
}