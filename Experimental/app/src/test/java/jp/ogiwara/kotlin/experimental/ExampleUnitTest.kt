package jp.ogiwara.kotlin.experimental

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.custom.async
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    fun hode(): Deferred<Int> = async(CommonPool) {
        Thread.sleep(1000)
        return@async 10
    }

    @Test
    fun asyncTest(){
        //hode().await() non suspendなfun内では呼び出せない
        hode()
    }

    suspend fun async2(){//呼び出し元のコンテキストを引き継ぐ
        hode().await()
        println("await")
        hode()
        println("non await")
    }
}