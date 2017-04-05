package jp.ogiwara.test

import java.util.function.Function

fun main(args: Array<String>){
    val a = Sample()
    a test 2
    a.params("a","b","c")
    a()
}

val lazyValue: String by lazy {
    println("created!")
    "Hello"
}

class Sample{

    infix fun test(x: Int){

    }

    operator fun plus(a: Any){

    }

    operator fun invoke(){
        println("Invoked!")
    }

    fun unit(): Unit{
        return Unit
        return
    }

    fun params(vararg strs: String){
        for(s: String in strs){
            print(s)
        }
    }
}