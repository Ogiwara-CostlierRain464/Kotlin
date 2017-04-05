package jp.ogiwara.AnitubeAPI.model

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import jp.ogiwara.AnitubeAPI.ANITUBE_URL_TOP
import org.jsoup.Jsoup
import jp.ogiwara.AnitubeAPI.utils.trim
import java.io.IOException
import java.util.regex.Pattern

/**
 * Created by ogiwara on 2017/04/04.
 */
data class Video(val title: String,
                 val views: String,
                 val imgUrl: String,
                 val videoUrl: String){

    val highMp4Url: String
        get(){
            val key = getKeyUrl()
            var result = String()
            var httpTransport = NetHttpTransport()
            try{
                val requestFactory = httpTransport.createRequestFactory()
                val url = GenericUrl(key)
                val req = requestFactory.buildGetRequest(url)
                val res = req.execute()
                try{
                    val html = res.parseAsString()
                    val sources = html.trim("sources: [{","}],")
                    val regex = "http:.*_hd\\.mp4"

                    val p = Pattern.compile(regex)

                    val m = p.matcher(sources)
                    while(m.find()){
                        result = m.group()
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
    val lowMp4Url: String
        get(){
            val key = getKeyUrl()
            var result = String()
            var httpTransport = NetHttpTransport()
            try{
                val requestFactory = httpTransport.createRequestFactory()
                val url = GenericUrl(key)
                val req = requestFactory.buildGetRequest(url)
                val res = req.execute()
                try{
                    val html = res.parseAsString()
                    val sources = html.trim("sources: [{","}],")
                    val regex = "http:.*[0-9]\\.mp4"

                    val p = Pattern.compile(regex)

                    val m = p.matcher(sources)
                    while(m.find()){
                        result = m.group()
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


    fun getKeyUrl(): String{
        var result = String()
        val httpTransport = NetHttpTransport()
        try {
            val requestFactory = httpTransport.createRequestFactory()
            val url = GenericUrl(videoUrl)
            val req = requestFactory.buildGetRequest(url)
            val res = req.execute()
            try{
                val html = res.parseAsString()
                val document = Jsoup.parse(html)
                val element = document.getElementById("videoPlayer")

                val code = element.outerHtml()

                val regex = "http://www\\.anitube\\.se/player/config\\.php\\?key=.*"
                val p = Pattern.compile(regex)

                val m = p.matcher(code)
                while (m.find()){
                    result = m.group().trim("","\"></script")
                }
            }finally {
                res.disconnect()
            }
        }catch (e: IOException){
            e.printStackTrace()
        }finally {
            httpTransport.shutdown()
        }
        println(result)
        return result
    }
}