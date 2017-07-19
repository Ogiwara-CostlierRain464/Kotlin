package jp.ogiwara.aileen3.subscribe

import jp.ogiwara.aileen3.other.model.Video
import jp.ogiwara.kotlin.cicle.Action

internal class LOAD: Action
internal class LOADED(val list: List<Video>): Action