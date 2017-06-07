package jp.ogiwara.kotlin.anitubelite

import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.brianegan.bansa.BaseStore
import jp.ogiwara.kotlin.anitubelite.reducer.reducer
import jp.ogiwara.kotlin.anitubelite.state.State

val store = BaseStore(State(), reducer)

class AnitubeLiteApplication: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}
