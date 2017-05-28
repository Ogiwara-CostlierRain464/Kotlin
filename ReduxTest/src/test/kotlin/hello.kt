fun main(args: Array<String>){
    println("Hello")


    val list = ArrayList<String>()


    var str: String = null //NO

    var str2: String? = null //OK!
    str2.length //NO
    str2?.length //OK!
    str2!!.length //OK(Throws NullPointerException)



}