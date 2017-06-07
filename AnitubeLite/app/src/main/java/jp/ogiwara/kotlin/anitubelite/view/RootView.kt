package jp.ogiwara.kotlin.anitubelite.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import com.brianegan.bansa.Subscription
import jp.ogiwara.kotlin.anitubelite.VideoListAdapter
import jp.ogiwara.kotlin.anitubelite.store
import trikita.anvil.Anvil
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView
import jp.ogiwara.kotlin.anitubelite.R
import jp.ogiwara.kotlin.anitubelite.action.PLAY
import jp.ogiwara.kotlin.anitubelite.action.SEARCH

class RootView(c: Context): RenderableView(c){

    var subscription: Subscription? = null

    val adapter = VideoListAdapter(context)

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

            linearLayout {
                size(FILL,WRAP)//w,h
                orientation(LinearLayout.HORIZONTAL)

                editText {
                    size(0,WRAP)
                    weight(1F)
                    singleLine(true)
                    id(R.id.search_keyword)
                    imeOptions(EditorInfo.IME_ACTION_DONE)
                }
                button {
                    size(WRAP, WRAP)
                    text("Search")
                    onClick {
                        store.dispatch(SEARCH((findViewById(R.id.search_keyword) as EditText).text.toString()))
                    }
                }
            }

            if(store.state.loading){
                progressBar {
                    layoutGravity(CENTER)
                }
            }else{
                listView {
                    init{
                        size(FILL,FILL)
                    }

                    adapter(adapter.update(store.state.videos))

                    onItemClick { parent, view, position, id ->
                        store.dispatch(PLAY(adapter.getItem(position)))
                    }
                }
            }

            if(store.state.playing != null){

                val intent = Intent()
                intent.setAction(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(store.state.playing),"video/*")
                context.startActivity(intent)
            }
        }
    }
}