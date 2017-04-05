package jp.ogiwara.test

fun main(args: Array<String>){
    (1..20).forEach {
        print(
                if(it%15==0){
                    "FizzBuzz"
                }else if(it%5==0){
                    "Fizz"
                }else if(it%3==0){
                    "Buzz"
                }else{
                    it
                }
        )
        print(" ")
    }
}