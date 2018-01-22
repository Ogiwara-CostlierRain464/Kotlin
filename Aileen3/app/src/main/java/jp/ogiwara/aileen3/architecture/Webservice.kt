package jp.ogiwara.aileen3.architecture

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<User>
}