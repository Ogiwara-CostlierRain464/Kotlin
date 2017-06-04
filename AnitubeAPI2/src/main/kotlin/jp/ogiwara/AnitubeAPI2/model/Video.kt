package jp.ogiwara.AnitubeAPI2.model

import jp.ogiwara.AnitubeAPI2.http.getBody
import jp.ogiwara.AnitubeAPI2.trim
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.regex.Pattern
import javax.print.attribute.standard.PrintQuality

data class Video(val title: String,
                 val views: String,
                 val imgUrl: String,
                 val videoUrl: String){

    companion object{
        const val KEY = "http://www\\.anitube\\.se/player/config\\.php\\?key=[0-9a-zA-Z]*"
        const val HD = "http://.*_hd\\.mp4"
        const val SD = "http://.*\\.mp4"
    }
    /**
     * Get Video's mp4 link
     *
     * Note: in some videos, there are no HD video link
     * @param quality false = 360p true = 720p
     */
    fun getMp4Url(quality: Boolean = false): String{
        var result = String()
        val html = getBody(getKeyUrl()).outerHtml()
        val p = Pattern.compile(if(quality) HD else SD)
        val m = p.matcher(html.trim(from = "sources: [{",to = "}],")) //Dirty implementation...
        while (m.find()){
            result = m.group()
        }
        return result
    }

    fun getMp4UrlAsync(quality: Boolean = false) = async(CommonPool){
        var result = String()
        val html = getBody(getKeyUrl()).outerHtml()
        val p = Pattern.compile(if(quality) HD else SD)
        val m = p.matcher(html.trim(from = "sources: [{",to = "}],")) //Dirty implementation...
        while (m.find()){
            result = m.group()
        }
        result
    }

    private fun getKeyUrl(): String{
        var result = String()

        val document = getBody(videoUrl)
        val videoPlayer = document.getElementById("videoPlayer")
        val code = videoPlayer.outerHtml()
        val p = Pattern.compile(KEY)

        val matcher = p.matcher(code)
        while (matcher.find()){
            result = matcher.group()
        }

        return result
    }
}
