package jp.ogiwara.cycle


interface Dispatcher {
    fun dispatch(action: Action)
}