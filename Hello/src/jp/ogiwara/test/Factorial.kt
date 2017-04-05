package jp.ogiwara.test

fun main(args: Array<String>){
    var i = 1
    (1..10).forEach {
        i *= it
    }
    println(i)
}