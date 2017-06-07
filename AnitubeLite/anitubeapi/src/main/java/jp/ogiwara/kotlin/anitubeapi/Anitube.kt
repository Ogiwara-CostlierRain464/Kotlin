package jp.ogiwara.kotlin.anitubeapi

import jp.ogiwara.kotlin.anitubeapi.method.GetTopVideosMethod
import jp.ogiwara.kotlin.anitubeapi.method.SearchMethod
import jp.ogiwara.kotlin.anitubeapi.model.Video
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

object Anitube{

    object URL{
        const val TOP = "http://www.anitube.se/"
        const val SEARCH = "http://www.anitube.se/search/?search_id="
    }

    val highLight: List<Video>
        get() = GetTopVideosMethod(GetTopVideosMethod.HIGHLIGHT).execute()

    val topRated: List<Video>
        get() = GetTopVideosMethod(GetTopVideosMethod.TOP_RATED).execute()

    val mostSeen: List<Video>
        get() = GetTopVideosMethod(GetTopVideosMethod.MOST_SEEN).execute()

    fun search(keyword: String): List<Video>{
        return SearchMethod(keyword).execute()
    }

    //region async
    /**
     * Note: in Kotlin 1.x, coroutines are experimental features.
     */

    val highLightAsync: Deferred<List<Video>>
        get() = async(CommonPool){
            GetTopVideosMethod(GetTopVideosMethod.HIGHLIGHT).execute()
        }

    val topRatedAsync: Deferred<List<Video>>
        get() = async(CommonPool){
            GetTopVideosMethod(GetTopVideosMethod.TOP_RATED).execute()
        }

    val mostSeenAsync: Deferred<List<Video>>
        get() = async(CommonPool){
            GetTopVideosMethod(GetTopVideosMethod.MOST_SEEN).execute()
        }

    fun searchAsync(keyword: String) = async(CommonPool){
        SearchMethod(keyword).execute()
    }
    //endregion
}