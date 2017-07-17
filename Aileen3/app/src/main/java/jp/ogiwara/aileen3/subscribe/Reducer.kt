package jp.ogiwara.aileen3.subscribe

import com.brianegan.bansa.Reducer

internal val reducer = Reducer<State> { state,action ->
    when(action){
        is LOAD -> {
            state
        }
        is LOADED -> {
            state
        }
        else -> state
    }
}
