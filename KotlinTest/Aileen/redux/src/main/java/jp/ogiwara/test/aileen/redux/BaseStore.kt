package jp.ogiwara.test.aileen.redux

class BaseStore(val state: State,val reducer: Reducer): Store{
    override fun dispatch(action: Action) {
        reducer.reduce(state,action)
    }
}