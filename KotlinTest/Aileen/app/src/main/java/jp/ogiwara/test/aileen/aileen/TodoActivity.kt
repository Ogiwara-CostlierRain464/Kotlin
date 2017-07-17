package jp.ogiwara.test.aileen.aileen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.brianegan.bansa.Action
import com.brianegan.bansa.BaseStore
import com.brianegan.bansa.Reducer
import com.github.florent37.viewanimator.ViewAnimator
import jp.ogiwara.test.aileen.aileen.model.Todo
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

class TodoActivity : AppCompatActivity() {

    val reducer = Reducer<State> { state, action ->
        when(action){
            is TOUCH -> {
                state.touch = true
                state
            }
            is INTENT -> {
                val intent = Intent(this,TodoActivity::class.java)
                startActivity(intent)
                state
            }
            else -> state
        }
    }


    val store = BaseStore(State(false),reducer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TodoActivityUI().setContentView(this)
    }

    inner class TodoActivityUI: AnkoComponent<TodoActivity> {

        override fun createView(ui: AnkoContext<TodoActivity>) = ui.apply {
            verticalLayout {
                val text =  textView("NON")
                button("CLICK") {
                    onClick {
                        ViewAnimator.animate(this@button)
                                .dp().translationX(-20f,0f)
                                .decelerate()
                                .duration(2000)
                                .start()
                        text.text = "TOUCHED"
                        //store.dispatch(TOUCH())
                    }
                }
            }
        }.view
    }

    data class State(var touch: Boolean)

    class TOUCH: Action
    class INTENT: Action
}
