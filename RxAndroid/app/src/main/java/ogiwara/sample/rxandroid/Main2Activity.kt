package ogiwara.sample.rxandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import rx.Observable
import rx.Observer
import rx.android.observables.ViewObservable

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val stream1 = ViewObservable.text(findViewById(R.id.edit_a) as TextView,true)
        val stream2 = ViewObservable.text(findViewById(R.id.edit_b) as TextView,true)

        val outPut = findViewById(R.id.output) as TextView

        val combine: Observable<Int> = Observable.combineLatest(
                stream1,
                stream2,
                func@ { inputAEvent,inputBEvent ->
                    var a = 0;
                    var b = 1;
                    val aText = inputAEvent.text.toString()
                    val bText = inputBEvent.text.toString()
                    if(!TextUtils.isEmpty(aText)){
                        a = aText.toInt()
                    }
                    if(!TextUtils.isEmpty(bText)){
                        b = bText.toInt()
                    }

                    return@func a+b
                }
        )
        combine.subscribe {
            outPut.text = it.toString()
        }
    }
}
