package jp.ogiwara.aileen3.subscribe

import jp.ogiwara.kotlin.cicle.Action
import jp.ogiwara.kotlin.cicle.Reducer

internal val reducer = object: Reducer<State>{
    override fun reduce(state: State, action: Action){
        when(action){
            is TOUCH -> {
                state.loading.set(true)
            }
        }
    }
}
