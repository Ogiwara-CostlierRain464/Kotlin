package jp.ogiwara.kotlin.experimental

import android.app.Application
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ExperimentalApplication : Application() {

    var githubService: GitHubService? = null
        private set

    override fun onCreate() {
        super.onCreate()

        setUpAPIClient()
    }

    fun setUpAPIClient(){
        val logging = HttpLoggingInterceptor {
            Log.d("API LOG",it)
        }

        logging.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        githubService = retrofit.create(GitHubService::class.java)
    }
}