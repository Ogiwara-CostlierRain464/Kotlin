package jp.ogiwara.test.kotlin.todo

import android.app.Application
import com.brianegan.bansa.BaseStore

val store = BaseStore(ApplicationState(), applicationReducer)

class TodoApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        store.dispatch(INIT())
    }

}