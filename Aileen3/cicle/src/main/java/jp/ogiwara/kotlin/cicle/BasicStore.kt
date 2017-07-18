package jp.ogiwara.kotlin.cicle

class BasicStore<S>(val state: S,val reducer: Reducer<S>){

    fun dispatch(action: Action){
        reducer.reduce(state,action)
    }

}