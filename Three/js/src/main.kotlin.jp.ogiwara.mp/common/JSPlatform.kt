package jp.ogiwara.mp.common

import jp.ogiwara.mp.common.data.*

actual class Date actual constructor(){
    actual fun getDate(): Int{
        TODO("not impled")
    }
}

actual fun platformMessage(message: Message){
   println("(JS) [${message.text}]")
}