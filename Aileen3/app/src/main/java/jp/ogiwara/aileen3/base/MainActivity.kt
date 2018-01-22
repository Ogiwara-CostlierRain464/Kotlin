package jp.ogiwara.aileen3.base

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jp.ogiwara.aileen3.i

class MainActivity : AppCompatActivity() {

    companion object{
        val REQUEST_ACCOUNT_PICKER = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RootView(this))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUEST_ACCOUNT_PICKER -> {
                if(resultCode == Activity.RESULT_OK && data != null && data.extras != null){
                    i(data.extras.getString(AccountManager.KEY_ACCOUNT_NAME),MainActivity::class)
                }
            }
        }
    }
}
