package jp.ogiwara.aileen3.architecture

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class UserRepository {
    //フィールド1 Retrofit
    var webservice: Webservice? = null
    //フィールド2 キャッシュ
    var userCache: UserCache? = null
    //3? ローカルデータベース?

    fun getUser(userId: String): LiveData<User>{
        val cached = userCache?.get(userId)
        if(cached != null){
            return cached
        }

        val data = MutableLiveData<User>()
        userCache?.put(userId,data)

        webservice!!.getUser(userId).enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                data.value = response!!.body()
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {

            }
        })
        return data
    }

    class UserCache{
        fun get(id: String): LiveData<User>?{
            return null
        }

        fun put(id: String,data: LiveData<User>){

        }
    }
}