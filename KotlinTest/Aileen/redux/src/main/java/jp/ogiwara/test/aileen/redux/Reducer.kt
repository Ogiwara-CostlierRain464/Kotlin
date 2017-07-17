package jp.ogiwara.test.aileen.redux

interface Reducer {
    fun reduce(state: State,action: Action)
}