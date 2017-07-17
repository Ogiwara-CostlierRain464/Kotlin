package jp.ogiwara.test.aileen.aileen

import android.animation.Animator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.brianegan.bansa.Action
import com.brianegan.bansa.BaseStore
import com.brianegan.bansa.Reducer
import com.brianegan.bansa.Subscription
import com.github.florent37.viewanimator.ViewAnimator
import org.jetbrains.anko.intentFor
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL
import trikita.anvil.RenderableView
import trikita.anvil.DSL.*

class MainActivity : AppCompatActivity() {

    val reducer = Reducer<State> { state,action ->
        when(action){
            is TOUCH -> {
                state.copy(touch = true)
            }
            is INTENT -> {
                startActivity(intentFor<TodoActivity>())
                state
            }
            else -> state
        }
    }

    val store = BaseStore(State(false),reducer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RootView())
    }

    inner class RootView: RenderableView(applicationContext){

        var subscription: Subscription? = null

        override fun onAttachedToWindow() {
            super.onAttachedToWindow()

            subscription = store.subscribe {
                Anvil.render()
            }
        }

        override fun onDetachedFromWindow() {
            super.onDetachedFromWindow()
            subscription?.unsubscribe()
        }

        override fun view() {
            linearLayout {
                size(FILL,FILL)
                orientation(LinearLayout.VERTICAL)

                textView {
                    size(WRAP,WRAP)
                    text(if(store.state.touch) "TOUCHED!" else "NON")
                }

                button {
                    size(WRAP,WRAP)
                    onClick {
                        store.dispatch(TOUCH())
                    }
                }
                button {
                    onClick {
                        store.dispatch(INTENT())
                    }
                }
            }
        }

    }

    data class State(var touch: Boolean)

    class TOUCH: Action
    class INTENT: Action

}
