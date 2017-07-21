package jp.ogiwara.aileen3.test1

import jp.ogiwara.aileen3.client
import jp.ogiwara.kotlin.cicle.Action
import jp.ogiwara.kotlin.cicle.Reducer
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

internal val reducer = object: Reducer<State>{
    override fun reduce(state: State, action: Action) {
        when(action){
            is LOAD -> {
                load()
                state.loading.set(true)
            }
            is LOADED -> {
                state.videos.addAll(action.list)
                state.loading.set(false)
            }
        }
    }
}

fun load(){
    launch(UI){
        store.dispatch(LOADED(client.topChartAsync.await()))
    }
}
