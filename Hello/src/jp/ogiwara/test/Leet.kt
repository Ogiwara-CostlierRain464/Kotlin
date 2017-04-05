package jp.ogiwara.test

fun main(args: Array<String>){
    val leet = hashMapOf<Char,Int>(
            'A' to 4,
            'E' to 3,
            'G' to 6,
            'I' to 1,
            'O' to 0,
            'S' to 5,
            'Z' to 2
    )
    "EMACSISBETTER".forEach {
        print(if(leet.containsKey(it))
                leet[it]
             else
                it
        )
    }
}
