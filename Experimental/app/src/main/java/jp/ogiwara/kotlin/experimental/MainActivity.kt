package jp.ogiwara.kotlin.experimental

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.bindView
import butterknife.bindViews
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val app : ExperimentalApplication by lazy {
        application as ExperimentalApplication
    }//GOOD!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.goto_async).setOnClickListener {
            startActivity(Intent(this,AsyncActivity::class.java))
        }

        app.githubService!!.listRepos("language:kotlin")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Repositories>() {
                    override fun onNext(t: Repositories?) {
                        //binding.listRepos.text = t.toString()
                    }

                    override fun onError(e: Throwable?) {
                        toast("Failed")
                        e!!.printStackTrace()
                    }

                    override fun onCompleted() {
                        toast("Complete")
                    }
                })
    }
}
