package jp.ogiwara.aileen3

import org.junit.Test
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxTest{

    @Test
    fun test(){
        var sub: Subscriber<in Int>? = null

        val observable = Observable.create<Int> { subscriber ->
            sub = subscriber

            Thread.sleep(100)
            sub?.onNext(1)
            Thread.sleep(100)
            sub?.onNext(2)
            Thread.sleep(100)
        }

        println("Observable created")

        observable.subscribeOn(Schedulers.newThread()).subscribe({
            println("onNext: $it")
        },{
           println("onError: $it")
        },{

        })

        println("Subscribed.")

        sub?.onNext(3)
        Thread.sleep(50)
        sub?.onNext(4)

    }

}
