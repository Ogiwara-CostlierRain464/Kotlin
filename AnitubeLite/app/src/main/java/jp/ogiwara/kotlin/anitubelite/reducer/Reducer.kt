package jp.ogiwara.kotlin.anitubelite.reducer

import android.content.Intent
import android.net.Uri
import com.brianegan.bansa.Reducer
import jp.ogiwara.kotlin.anitubeapi.Anitube
import jp.ogiwara.kotlin.anitubelite.action.INTENT
import jp.ogiwara.kotlin.anitubelite.action.LOADED
import jp.ogiwara.kotlin.anitubelite.action.PLAY
import jp.ogiwara.kotlin.anitubelite.action.SEARCH
import jp.ogiwara.kotlin.anitubelite.state.State
import jp.ogiwara.kotlin.anitubelite.store
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

val reducer = Reducer<State> { state,action ->
    when(action){
        is SEARCH -> {
            if(action.keyword.isEmpty()){
                state
            }else{
                launch(UI){
                    val videos2 = Anitube.searchAsync(action.keyword).await()
                    val dispatch: Any = store.dispatch(LOADED(videos2))
                }
                state.copy(state.videos, true, state.playing)
            }
        }
        is PLAY -> {
            launch(UI){
                val mp4 = action.video.getMp4UrlAsync().await()
                val dispatch: Any = store.dispatch(INTENT(mp4))
            }
            state
        }
        is LOADED -> {
            state.copy(action.videos,false,state.playing)
        }
        is INTENT -> {
            state.copy(state.videos,state.loading,action.mp4Url)
        }
        else -> state
    }
}
