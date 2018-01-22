package jp.ogiwara.aileen3

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import jp.ogiwara.aileen3.other.client.YouTubeClient


//Note: 認証なしのクライアント
val client = YouTubeClient()

//TODO DI
class App : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
    }

}