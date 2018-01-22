package jp.ogiwara.mp.common

import jp.ogiwara.mp.common.data.*

expect class Date(){
    fun getDate(): Int
}

expect fun platformMessage(message: Message)