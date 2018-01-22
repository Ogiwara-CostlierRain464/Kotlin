package jp.ogiwara.aileen3.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.squareup.moshi.Json
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.base.MainActivity
import jp.ogiwara.aileen3.i
import jp.ogiwara.aileen3.other.API_KEY
import jp.ogiwara.aileen3.setting.SettingActivity
import okhttp3.Response
import org.jetbrains.anko.find
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class OAuthTestFragment: Fragment(){

    val CLIENT_ID = "592903206862-3cosn0fvtg7lpsfrub477eoake2vinqo.apps.googleusercontent.com"
    val CLIENT_SECRET = "fkGUHdmZDtrObvZG2bCeYKoC"
    val REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob"
    val SCOPE = "https://www.googleapis.com/auth/youtube"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_oauth,container,false)

        root!!.find<Button>(R.id.oauth_button).setOnClickListener {
            //Test1: Intent
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://accounts.google.com/o/oauth2/auth?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URL&response_type=code&scope=https://www.googleapis.com/auth/youtube")
            context.startActivity(intent)
            //Test2: Failed cause: DEX FILE ERROR
            /*val oauthActivity = GoogleAccountCredential.usingOAuth2(activity, arrayListOf("https://www.googleapis.com/auth/youtube"))
            activity.startActivityForResult(oauthActivity.newChooseAccountIntent(),MainActivity.REQUEST_ACCOUNT_PICKER)*/
        }
        root.find<Button>(R.id.setting_button).setOnClickListener {
            val intent = Intent(context,SettingActivity::class.java)
            startActivity(intent)
        }

        root.find<Button>(R.id.oauth_button2).setOnClickListener {
            val token = root.find<AppCompatEditText>(R.id.oauth_token).text.toString()
            if(token.isEmpty()){
                return@setOnClickListener
            }
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://accounts.google.com/o/oauth2/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            val service = retrofit.create(OAuthService::class.java)
            service.OAuth2(token,CLIENT_ID, CLIENT_SECRET,REDIRECT_URL,"authorization_code")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        i(it.toString(),OAuthTestFragment::class)
                    },{
                        i("${it.message}",OAuthTestFragment::class)
                    })
        }

        return root
    }

    data class Result(val access_token: String, val token_type: String, val expires_in: String, val refresh_token: String)

    interface OAuthService{

        @FormUrlEncoded
        @POST("token")
        fun OAuth2(
                @Field("code") code: String,
                @Field("client_id") clientId: String,
                @Field("client_secret") clientSecret: String,
                @Field("redirect_uri") redirectUri: String,
                @Field("grant_type") grantType: String
        ): Observable<Result>

        @GET("auth")
        fun OAuth(
                @Query("client_id") clientId: String,
                @Query("redirect_uri") redirectUri: String,
                @Query("response_type") responseType: String,
                @Query("scope") scope: String): Observable<Response>
    }
}