import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

interface Hoge
interface Fuga

interface Piyo: Hoge,Fuga

class Bar<T> where T: Hoge, T: Fuga//やだわー

/**
 * ジェネリクスの変性(variance)
 * 不変、共変、反変
 *
 */

enum class state{
    ADD,MULTIPLY
}

@Test
fun rx2(){

    var calcMethod = state.ADD

    val flowAble = Flowable.interval(300L,TimeUnit.MILLISECONDS)
            .take(7)
            .scan { sum,data ->
                when(calcMethod){//参照等価なし
                    state.ADD -> sum + data
                    state.MULTIPLY -> sum * data
                }
            }

    flowAble.subscribe(::println)

    Thread.sleep(1000)
    calcMethod = state.MULTIPLY
    Thread.sleep(5000)

}

@Test
fun rxSample(){

    val flowable: Flowable<String> = Flowable.create(lamda@ {emmiter ->
        val data = arrayOf("Hello","こんにちは")
        data.forEach {
            if(emmiter.isCancelled){
                return@lamda
            }

            emmiter.onNext(it)
        }

        emmiter.onComplete()
    },BackpressureStrategy.BUFFER)//超過したデータはバッファ

    flowable
            .observeOn(Schedulers.computation())//computational は計算の意味
            .subscribe(object: Subscriber<String>{

                var subscription: Subscription? = null

                override fun onSubscribe(s: Subscription) {
                    subscription = s
                    subscription?.request(1L)
                }

                override fun onNext(t: String?) {
                    val threadName = Thread.currentThread().name
                    println("$threadName: $t")

                    subscription?.request(1L)
                }

                override fun onComplete() {//ここでExceptionを発生させても、だれもcatchしません
                    println("${Thread.currentThread().name}: 完了")
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }
            })

    Thread.sleep(500L)
}

@Test
fun generics(){

    Bar<Piyo>()
    //Bar<Fuga>() Fuga !: Hoge,Fuga

    //NORMAL


    val a:CharSequence = "w"
    //val b: String = a Can't sub cast!

    val c:String = "e"
    val d: CharSequence = c //OK super cast

    //Generics


    class Fun1<T>(val o: T)

    val e: Fun1<String> = Fun1("string")
    //val f: Fun1<CharSequence> = e 不変性

    val g: Fun1<CharSequence> = Fun1("string")
    //val h: Fun1<String> = g 不変性


    //型投影を利用!
    //共変
    //Tは出る型のみに使う約束
    //super cast のみ

    class Fun2<out T>(val o: T){
        //fun a(j: T) = Unit
        fun b(): T? = null
    }

    val i: Fun2<String> = Fun2("string")
    val j: Fun2<CharSequence> = i //OK!


    val k: Fun2<CharSequence> = Fun2("string")
    //val l: Fun2<String> = k Can't sub cast!


    //反変
    //Tは入る型のみに使う約束
    //sub cast のみ

    class Fun3<in T>{
        fun a(j: T) = Unit
        //fun b(): T? = null
    }

    val m: Fun3<String> = Fun3<String>()
    //val n: Fun3<CharSequence>  m Can't super cast!

    val o: Fun3<CharSequence> = Fun3()
    val p: Fun3<String> = o //OK!

    //スター投影
    //単なる in Nothing や out Any? の糖衣構文
    //Nothing: あらゆる型のサブタイプ


    class Container<T>(val value: T)

    val q: Container<*> = Container<Int>(3)
    val r: Container<*> = Container<String>("www")


    //具象型
    class A{
        inline fun <reified T> a() = null
    }
}
