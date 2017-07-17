package jp.ogiwara.aileen3.topchart

import com.brianegan.bansa.Action
import jp.ogiwara.aileen3.other.model.Video

class LOAD: Action
class LOADED(val list: List<Video>): Action
class INIT: Action
class PLAY: Action