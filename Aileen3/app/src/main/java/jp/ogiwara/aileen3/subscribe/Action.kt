package jp.ogiwara.aileen3.subscribe

import com.brianegan.bansa.Action
import jp.ogiwara.aileen3.other.model.Video

class LOAD: Action
class LOADED(val list: List<Video>): Action