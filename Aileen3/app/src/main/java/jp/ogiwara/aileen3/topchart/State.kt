package jp.ogiwara.aileen3.topchart

import jp.ogiwara.aileen3.other.model.Video

data class State(val videos: List<Video> = arrayListOf<Video>(),
                 val loading: Boolean = true)