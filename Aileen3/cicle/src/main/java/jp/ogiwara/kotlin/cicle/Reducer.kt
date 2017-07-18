package jp.ogiwara.kotlin.cicle

interface Reducer<S>{
    fun reduce(state: S,action: Action)
}