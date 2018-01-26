package jp.ogiwara.cycle

class Store(private val dispatcher: Dispatcher) {

    fun dispatch(action: Action){
        dispatcher.dispatch(action)
    }
}