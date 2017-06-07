package jp.ogiwara.kotlin.anitubelite.state

import jp.ogiwara.kotlin.anitubeapi.model.Video

data class State(var videos: List<Video> = arrayListOf(),
                 var loading: Boolean = false,
                 var playing: String? = null)