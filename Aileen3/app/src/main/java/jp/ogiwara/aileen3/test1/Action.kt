package jp.ogiwara.aileen3.test1

import jp.ogiwara.aileen3.other.model.Video
import jp.ogiwara.kotlin.cicle.Action

internal class LOAD: Action
internal data class LOADED(val list: List<Video>): Action
