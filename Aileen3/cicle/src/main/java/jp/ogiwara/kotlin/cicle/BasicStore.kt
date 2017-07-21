package jp.ogiwara.kotlin.cicle

import android.util.Log

class BasicStore<S>(val state: S,val reducer: Reducer<S>){

    fun dispatch(action: Action){
        reducer.reduce(state,action)
        Log.d("dispatched",action.toString())
    }

}