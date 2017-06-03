package jp.ogiwara.AnitubeAPI

import jp.ogiwara.AnitubeAPI.method.GetTopVideosMethod
import jp.ogiwara.AnitubeAPI.method.SearchMethod
import jp.ogiwara.AnitubeAPI.model.Video


const val ANITUBE_URL_TOP = "http://www.anitube.se/"
const val ANITUBE_URL_SEARCH = "http://www.anitube.se/search/?search_id="

object Anitube{

    object URL{
        const val TOP = "http://www.anitube.se/"
        const val SEARCH = "http://www.anitube.se/search/?search_id="
    }

    val highLight: List<Video>
        get() = GetTopVideosMethod(GetTopVideosMethod.FRAGMENT.HIGHLIGHT).execute()


    fun getHighlight(): ArrayList<Video>{
        return GetTopVideosMethod(GetTopVideosMethod.FRAGMENT.HIGHLIGHT).execute()
    }

    fun getTopRated() : ArrayList<Video>{
        return GetTopVideosMethod(GetTopVideosMethod.FRAGMENT.TOP_RATED).execute()
    }

    fun getMostSeen(): ArrayList<Video>{
        return GetTopVideosMethod(GetTopVideosMethod.FRAGMENT.MOST_SEEN).execute()
    }

    fun search(query: String) : ArrayList<Video>{
        return SearchMethod(query).execute()
    }

}