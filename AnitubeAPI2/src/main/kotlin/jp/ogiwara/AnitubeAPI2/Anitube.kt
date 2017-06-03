package jp.ogiwara.AnitubeAPI2

import jp.ogiwara.AnitubeAPI2.method.GetTopVideosMethod
import jp.ogiwara.AnitubeAPI2.method.SearchMethod
import jp.ogiwara.AnitubeAPI2.model.Video
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

object Anitube{

    object URL{
        const val TOP = "http://www.anitube.se/"
        const val SEARCH = "http://www.anitube.se/search/?search_id="
    }

    val highLight: Deferred<List<Video>>
        get() = async(CommonPool){
            GetTopVideosMethod(GetTopVideosMethod.HIGHLIGHT).execute()
        }

    val topRated: Deferred<List<Video>>
        get() = async(CommonPool){
            GetTopVideosMethod(GetTopVideosMethod.TOP_RATED).execute()
        }

    val mostSeen: Deferred<List<Video>>
        get() = async(CommonPool){
            GetTopVideosMethod(GetTopVideosMethod.MOST_SEEN).execute()
        }

    fun search(keyword: String) = async(CommonPool){
        return@async SearchMethod(keyword).execute()
    }
}