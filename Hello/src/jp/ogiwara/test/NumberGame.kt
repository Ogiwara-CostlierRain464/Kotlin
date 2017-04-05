package jp.ogiwara.test

fun main(args: Array<String>){
    var result = 2789
    while (result >= 10){
        var (a,b) = div(result)
        result = a+b
    }
    println(result)
}

fun div(num: Int): Pair<Int,Int>{
    var a = num/10
    var b = num%10
    return Pair(a,b)
}
