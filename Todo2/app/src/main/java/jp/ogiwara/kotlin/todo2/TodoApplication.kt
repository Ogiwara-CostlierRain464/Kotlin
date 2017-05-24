package jp.ogiwara.kotlin.todo2

import android.app.Application
import com.brianegan.bansa.BaseStore
import jp.ogiwara.kotlin.todo2.action.INIT
import jp.ogiwara.kotlin.todo2.reducer.applicationReducer
import jp.ogiwara.kotlin.todo2.state.TodoApplicationState

val store = BaseStore(TodoApplicationState(), applicationReducer)

class TodoApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        store.dispatch(INIT())
    }

}