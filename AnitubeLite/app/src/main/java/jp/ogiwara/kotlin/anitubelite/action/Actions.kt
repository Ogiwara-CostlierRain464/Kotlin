package jp.ogiwara.kotlin.anitubelite.action

import com.brianegan.bansa.Action
import jp.ogiwara.kotlin.anitubeapi.model.Video

data class SEARCH(val keyword: String): Action
data class LOADED(val videos: List<Video>): Action
data class PLAY(val video: Video): Action
data class INTENT(val mp4Url: String): Action