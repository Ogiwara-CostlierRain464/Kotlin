package jp.ogiwara.aileen3

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import jp.ogiwara.aileen3.other.client.YouTubeClient

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    /*fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("jp.ogiwara.aileen3", appContext.packageName)
    }*/

    @Test
    fun tokenTest(){
        val client = YouTubeClient()
        client.topChart
        client.topChart.forEach(::println)
    }
}
