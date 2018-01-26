import jp.ogiwara.hello.Sample
import org.junit.Test

class SampleTest {
    @Test
    fun message(){
        assert(Sample().message != "hello")
    }
}

