package jp.ogiwara.aileen3

import android.util.Log
import com.google.api.services.youtube.model.SearchResult
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import jp.ogiwara.aileen3.client.APIService
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Test
    fun test2(){
        Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder()
                                .serializeNulls()
                                .enableComplexMapKeySerialization()
                                .create()
                ))
                .build()
                .create(APIService::class.java)
                .getYoutubeFeeds(KEY,"UCjXfkj5iapKHJrhYfAF9ZGg","snippet","date")
                .enqueue(object : Callback<Hoge>{
                    override fun onResponse(call: Call<Hoge>?, response: Response<Hoge>?) {
                        println("TEST ${response?.body()}")
                    }

                    override fun onFailure(call: Call<Hoge>?, t: Throwable?) {
                        println("ERROR ${t.toString()}")
                    }
                })

        Thread.sleep(3000)
    }

    //Note Gsonのばぐ @link {http://qiita.com/yamacraft/items/cc1230d4bed1d2405f37}
    //http://qiita.com/yysk/items/e549ba40bc2accfdff35
    //回避法: FieldをObject(Any)に

    data class Hoge(var kind: String,
                    var items: List<SearchResult>)

    interface APIService {

        @GET("search")
        fun getYoutubeFeeds(@Query("key") devKey: String,
                            @Query("channelId") channelId: String,
                            @Query("part") part: String,
                            @Query("order") order: String): Call<Hoge>

    }
}

const val KEY: String = "AIzaSyA0zT1Dv_qTLzyKXK2fYrbnlRDcIksRRfg"