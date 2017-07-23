package jp.ogiwara.aileen3

import android.util.Log
import com.google.api.services.youtube.model.SearchResult
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import jp.ogiwara.aileen3.other.client.YouTubeClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
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
import rx.Completable
import rx.Observable
import rx.Observer
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ExampleUnitTest {

    fun testRx(){
        val observable = Observable.create<Int>{ sub ->
            (1..20).forEach {
                sub.onNext(it)
            }
            sub.onCompleted()
        }

        observable.map {
            if(it % 15 == 0){
                "FizzBuzz"
            }else if(it % 3 == 0){
                "Fizz"
            }else if(it % 5 == 0){
                "Buzz"
            }else{
                it.toString()
            }
        }.subscribe(::println)


    }


    fun textRx2(){
        //スワイプ時にonNextに追加すれば?
        Observable.create<Int> { sub ->//OnSubscribe

            sub.onNext(1)

            async(CommonPool){
                delay(5000)
                sub.onNext(99999)

                sub.onCompleted()
            }
        }.subscribe(::println)

        Thread.sleep(10000)


    }


    fun textRx3(){
        val onsubscribe = object: Observable.OnSubscribe<Int>{
            override fun call(t: Subscriber<in Int>?) {
                t?.onNext(1)
            }
        }
        A.b(onsubscribe).subscribe(::println)

        async(CommonPool){
            delay(3000)
            onsubscribe.call(object : Subscriber<Int>(){
                override fun onNext(t: Int?) {
                    println(t)
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {

                }
            })
        }

        Thread.sleep(5000)
    }


    fun tokenTest(){
        val client = YouTubeClient()
        client.topChart
        client.topChart.forEach(::println)
    }

    fun youtubeOnRetroFit(){
        RestAdapter
    }


    object A{
        fun b(f: Observable.OnSubscribe<Int>): Observable<Int>{
            return Observable.create<Int>(f)
        }
    }
}
