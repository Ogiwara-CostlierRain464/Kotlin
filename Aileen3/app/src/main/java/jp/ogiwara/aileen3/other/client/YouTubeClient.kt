package jp.ogiwara.aileen3.other.client

import android.accounts.AccountManager
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.YouTubeRequestInitializer
import jp.ogiwara.aileen3.cutEnd
import jp.ogiwara.aileen3.i
import jp.ogiwara.aileen3.other.API_KEY
import jp.ogiwara.aileen3.other.APP_NAME
import jp.ogiwara.aileen3.other.ISO8601DurationConverter
import jp.ogiwara.aileen3.other.MAX_ITEM
import jp.ogiwara.aileen3.other.model.Video
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import rx.Observable
import java.io.IOException
import java.lang.StringBuilder
import java.util.*
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * TODO OAuth
 */
class YouTubeClient {

    companion object{
        var token: String? = null
    }

    val credential = HttpRequestInitializer {}

    //Next Page Token - - - > Success!
    val topChart: List<Video>
        get(){

            if(token == null){
                val stringBuilder = StringBuilder()
                try{
                    val youtube = createYouTube()
                    val req = youtube.videos().list("id")
                    req.chart = "mostPopular"
                    req.maxResults = MAX_ITEM
                    req.fields = "items,nextPageToken"


                    val res = req.execute()
                    res.items.forEach {
                        stringBuilder.append("${it.id},")
                    }

                    token = res.nextPageToken
                }catch (e: IOException){
                    e.printStackTrace()
                }

                return loadIds(stringBuilder.toString().cutEnd())
            }else{
                val stringBuilder = StringBuilder()
                try{
                    val youtube = createYouTube()
                    val req = youtube.videos().list("id")
                    req.chart = "mostPopular"
                    req.maxResults = MAX_ITEM
                    req.pageToken = token
                    req.fields = "items,nextPageToken"

                    val res = req.execute()
                    res.items.forEach {
                        stringBuilder.append("${it.id},")
                    }

                    token = res.nextPageToken
                }catch (e: IOException){
                    e.printStackTrace()
                }

                return loadIds(stringBuilder.toString().cutEnd())
            }
        }

    val topChartAsync: Deferred<List<Video>>
        get() = async(CommonPool){
            topChart
        }

    val topChartObservable: Observable<Video>
        get() =
            Observable.unsafeCreate<Video>{ sub ->
                topChart.forEach {
                    sub.onNext(it)
                }
                sub.onCompleted()
            }


    fun loadIds(ids: String): List<Video>{
        val result = LinkedList<Video>() //TODO メモリに優しく!
        try{
            val youtube = createYouTube()

            val req = youtube.videos().list("id,snippet,statistics,contentDetails")
            req.key = API_KEY
            req.fields = "items(id,snippet/channelTitle,snippet/title,snippet/thumbnails/default/url,snippet/thumbnails/high/url,contentDetails/duration,statistics/viewCount)"
            req.maxResults = MAX_ITEM
            req.id = ids
            i("Loading videos: $ids")

            val res = req.execute()
            res.items.map{
                convertVideoModel(it)
            }.forEach {
                result.add(it)
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
        return result
    }

    private fun convertVideoModel(raw: com.google.api.services.youtube.model.Video): Video{
        val isoTime = raw.contentDetails?.duration ?: "PT5M42S"
        val time = ISO8601DurationConverter.convert(isoTime)

        //TODO 設定
        val thumbnail = raw.snippet.thumbnails.default.url

        return Video(id = raw.id,
                     title = raw.snippet?.title ?: "",
                     channelName = raw.snippet?.channelTitle ?: "",
                     thumbnailUrl = thumbnail,
                     duration = time,
                     viewCount = raw.statistics?.viewCount?.toString() ?: "")
    }

    private fun createYouTube() = YouTube.Builder(
            AndroidHttp.newCompatibleTransport(),
            JacksonFactory(),
            credential
            )
            .setYouTubeRequestInitializer(
                    YouTubeRequestInitializer(API_KEY)
            )
            .setApplicationName(APP_NAME)
            .build()
}