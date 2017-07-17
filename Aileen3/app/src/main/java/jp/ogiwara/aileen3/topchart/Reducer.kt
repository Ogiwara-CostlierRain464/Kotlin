package jp.ogiwara.aileen3.topchart

import com.brianegan.bansa.Reducer
import jp.ogiwara.aileen3.client
import jp.ogiwara.aileen3.i
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import trikita.anvil.Anvil

internal val reducer = Reducer<State> { state, action ->
    when(action){
        is INIT -> {
            load()
            state.copy(loading = true)
        }
        is LOAD -> {
            load()
            state.copy(loading = true)
        }
        is LOADED -> {
            i("Loaded",TopChartFragment::class)
            state.copy(action.list,false)
        }
        is PLAY -> {
            //外部通信!
            state
        }
        else -> state
    }
}

fun load(){
    launch(UI){
        store.dispatch(LOADED(client.topChartAsync.await()))
    }
}

