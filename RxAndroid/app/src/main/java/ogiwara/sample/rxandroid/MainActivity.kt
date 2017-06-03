package ogiwara.sample.rxandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.realm.Realm
import ogiwara.sample.rxandroid.model.User
import rx.Observable
import rx.android.events.OnClickEvent
import rx.android.observables.ViewObservable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counter = findViewById(R.id.count) as TextView

        findViewById(R.id.goto_2).setOnClickListener {
            val intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
        findViewById(R.id.goto_3).setOnClickListener {
            val intent = Intent(this,RealmActivity::class.java)
            startActivity(intent)
        }

        val stream1 = ViewObservable.clicks(findViewById(R.id.increment1))
        val stream2 = ViewObservable.clicks(findViewById(R.id.increment2))
        val merged: Observable<OnClickEvent> = Observable.merge(stream1,stream2)

        merged.subscribe {
            counter.text = (counter.text.toString().toInt() + 1).toString()
        }

        val obj2 = Realm.getDefaultInstance().where(User::class.java).equalTo("id",1).findAll()
        toast(obj2.toString())
    }
}
