package ogiwara.sample.rxandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import rx.Observable
import rx.android.events.OnClickEvent
import rx.android.observables.ViewObservable
import rx.functions.Action1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counter = findViewById(R.id.count) as TextView

        val stream1 = ViewObservable.clicks(findViewById(R.id.increment1))
        val stream2 = ViewObservable.clicks(findViewById(R.id.increment2))
        val merged: Observable<OnClickEvent> = Observable.merge(stream1,stream2)

        merged.subscribe {
            counter.text = (counter.text.toString().toInt() + 1).toString()
        }
    }
}
