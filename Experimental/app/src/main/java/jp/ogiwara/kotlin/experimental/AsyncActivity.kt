package jp.ogiwara.kotlin.experimental

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import butterknife.bindView
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.enabled
import org.jetbrains.anko.toast

class AsyncActivity : AppCompatActivity() {

    val button: Button by bindView(R.id.click)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        button.setOnClickListener {
            onClick()
        }
    }

    fun onClick() = launch(UI){
        button.enabled = false

        val ten: Deferred<Int> = AsyncModel.returnTenAsync()//asyncを呼び出した時点で計算はスタートしている
        val twenty: Deferred<Int> = AsyncModel.returnTwentyAsync()
        val result = ten.await() * twenty.await()

        toast("result = $result")
        button.enabled = true
    }
}

object AsyncModel{
    fun returnTenAsync() = async(CommonPool) {
        delay(1000)
        return@async 10
    }


    fun returnTwentyAsync() = async(CommonPool){
        delay(2000)//Thread.sleepはやめよ
        return@async 20
    }
}