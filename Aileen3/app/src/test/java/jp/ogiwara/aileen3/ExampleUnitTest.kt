package jp.ogiwara.aileen3

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okio.BufferedSource
import okio.Timeout
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import org.jetbrains.anko.custom.async
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.FormElement
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import rx.Observable
import rx.Subscriber
import java.io.IOException
import java.net.CookieManager
import java.net.CookieStore
import java.security.SecureRandom
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class ExampleUnitTest {

    @Test
    fun spellTest(){
        val spell = getSpell()
        println("Spell: $spell")
        val sig = makeSig(spell)
        println("Sig: $sig")
        getToken(spell,sig,UUID.randomUUID().toString())

        //https://api.lobi.co/1/me?platform=android&lang=ja&token=a5cdb2804c213e681215a7dc31f3260d3feb77b3
    }

    fun getSpell(): String{

        var spell = ""

        val cookieHandler = CookieManager()

        val okHttp = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieHandler))
                .addInterceptor { chain ->
                    val original = chain.request()
                    val req = original.newBuilder()
                            .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Mobile Safari/537.36")
                            .header("Content-Type","application/x-www-form-urlencoded")
                            .method(original.method(),original.body())
                            .build()

                    chain.proceed(req)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://lobi.co/inapp/")
                .client(okHttp)
                .build()
                .create(LobiService::class.java)

        retrofit.getCsrf().enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val htmlString = response?.body()?.string()
                val html = Jsoup.parse(htmlString)
                val form = html.select("form").first() as FormElement
                val csrf = form.formData().get(0).value()

                retrofit.getSpell2(csrf, "yushiogiwara@icloud.com", "Maiko7039").enqueue(object: Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        println(response)
                        spell = response!!.headers().get("Location")!!.replace("nakamapbridge://signin?spell=","")
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) = println("fail2: $t")
                })
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) = println("fail1: $t")
        })


        Thread.sleep(3000)

        return spell
    }

    fun makeSig(spell: String): String{
        val secret = "db6db1788023ce4703eecf6aa33f5fcde35a458c"
        val algo = "HmacSHA1"

        //val decodedKey = Hex.decodeHex(secret.toCharArray())
        val keySpec = SecretKeySpec(secret.toByteArray(Charsets.US_ASCII),algo)
        val mac = Mac.getInstance(algo)
        mac.init(keySpec)
        val dataBytes = spell.toByteArray(Charsets.US_ASCII)
        val sigBytes = mac.doFinal(dataBytes)
        return String(Base64.encodeBase64(sigBytes),Charsets.US_ASCII)
    }

    fun getToken(spell: String,sig: String,uuid: String){

        val cookieHandler = CookieManager()

        val okHttp = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieHandler))
                .addInterceptor { chain ->
                    val original = chain.request()
                    val req = original.newBuilder()
                            .header("User-Agent","LobiAPI-of-AmenixProject")
                            .header("Host","api.lobi.co")
                            .method(original.method(),original.body())
                            .build()

                    chain.proceed(req)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.lobi.co/1/")
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .client(okHttp)
                .build()
                .create(LobiService2::class.java)

        retrofit.getToken(mapOf(
                "device_uuid" to uuid,
                "sig" to sig,
                "spell" to spell,
                "lang" to "ja"
        )).enqueue(object: Callback<LobiRes>{
            override fun onResponse(call: Call<LobiRes>?, response: Response<LobiRes>?) {
                val token = response!!.body()!!.token
                println(response!!.body())
                retrofit.getMe("android","ja",token)
                        .enqueue(object: Callback<Users.User>{
                            override fun onResponse(call: Call<Users.User>?, response: Response<Users.User>?) {
                                println(response!!.body())
                            }

                            override fun onFailure(call: Call<Users.User>?, t: Throwable?){
                                t?.printStackTrace()
                            }
                        })
            }

            override fun onFailure(call: Call<LobiRes>?, t: Throwable?) = println("fail: $t")
        })

        Thread.sleep(5000)
    }

    @Test
    fun streamingTest(){

        val okHttp = OkHttpClient.Builder()
                .connectTimeout(1L, TimeUnit.HOURS)
                .readTimeout(1L,TimeUnit.HOURS)
                .writeTimeout(1L,TimeUnit.HOURS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://stream.lobi.co/")
                .client(okHttp)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(Streaming::class.java)

        retrofit.test("2","dda7952111c09aeb9b3a3f024f92e2f3c5574249","a5cdb2804c213e681215a7dc31f3260d3feb77b3")
                .flatMap {
                    events(it.source())
                }
                .subscribe(::println)

    }

    fun events(source: BufferedSource): Observable<String>{
        return Observable.create(Observable.OnSubscribe<String>(){ subscriber ->
            try{
                while(!source.exhausted()){ // and cancel token... must be mutex.
                    subscriber.onNext(source.readUtf8Line())
                }
                subscriber.onCompleted()
            }catch (e: IOException){
                subscriber.onError(e)
            }
        })
    }

    interface Streaming{

        @GET("{version}/group/{groupId}")
        @retrofit2.http.Streaming
        fun test(@Path("version") version: String,
                 @Path("groupId") groupId: String,
                 @Query("token") accessToken: String): Observable<ResponseBody>
    }

    interface LobiService{

        @GET("signin/password?webview=1")
        fun getCsrf(): Call<ResponseBody>

        @FormUrlEncoded
        @Headers("Referer: https://lobi.co/inapp/signin/password",
                "Origin: https://lobi.co")
        @POST("signin/password")
        fun getSpell2(@Field("csrf_token") csrf: String,
                      @Field("email") email: String,
                      @Field("password") password: String): Call<ResponseBody>

        @FormUrlEncoded
        @Headers(
                "Accept-Encoding: gzip",
                "User-Agent: LobiAPI-of-AmenixProject",
                "Host: api.lobi.co")
        @POST("https://api.lobi.co/1/signin_confirmation")
        fun getToken(@FieldMap params: Map<String,String>): Call<ResponseBody>
    }

    interface LobiService2{
        @FormUrlEncoded
        @POST("signin_confirmation")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        fun getToken(@FieldMap params: Map<String,String>): Call<LobiRes>

        @GET("me")
        fun getMe(@Query("platform") platform: String,
                  @Query("lang") lang: String,
                  @Query("token") token: String): Call<Users.User>
    }

    data class LobiRes(val icon: String,val uid: String,val name: String,val token: String)

    //group dda7952111c09aeb9b3a3f024f92e2f3c5574249
    //token a5cdb2804c213e681215a7dc31f3260d3feb77b3
    //
}

data class User2(val name: String)