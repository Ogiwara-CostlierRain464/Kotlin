package jp.ogiwara.aileen3

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.google.api.client.http.HttpRequestInitializer
import jp.ogiwara.aileen3.other.client.YouTubeClient


//Note: 認証なしのクライアント
val client = YouTubeClient()

//TODO イベントバイプ(EventBus)
//TODO DI
class App : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

}