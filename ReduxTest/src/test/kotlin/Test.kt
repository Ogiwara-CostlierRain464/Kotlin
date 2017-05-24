interface Hoge
interface Fuga

interface Piyo: Hoge,Fuga

class Bar<T> where T: Hoge, T: Fuga//やだわー

/**
 * ジェネリクスの変性(variance)
 * 不変、共変、反変
 *
 */
fun main(args: Array<String>){

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
