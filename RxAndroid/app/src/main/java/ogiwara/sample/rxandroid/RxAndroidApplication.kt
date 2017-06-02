package ogiwara.sample.rxandroid

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RxAndroidApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        prepareRealm()

    }

    fun prepareRealm(){
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
    }

}