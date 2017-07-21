package jp.ogiwara.aileen3.topchart

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.brianegan.bansa.Subscription
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.adapter.NormalVideoListAdapter
import jp.ogiwara.aileen3.i
import org.jetbrains.anko.find
import trikita.anvil.Anvil
import trikita.anvil.DSL.*
import trikita.anvil.support.v4.Supportv4DSL.refreshing
import trikita.anvil.RenderableView
import trikita.anvil.support.v4.Supportv4DSL.onRefresh
import trikita.anvil.support.v4.Supportv4DSL.swipeRefreshLayout

class RootView(c: Context):  RenderableView(c){

    var subscription: Subscription? = null

    val adapter = NormalVideoListAdapter(context)

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

        //region xml

        //Note XMLで再renderができない… Fieldにキャッシュ?
        /*xml(R.layout.fragment_common){
            i("View",TopChartFrment::class)

            val listView: ListView = if(listView == null){
                                        listView = (withId(R.id.list_view){} as ListView)
                                        listView as ListView
                                     }else{
                                        listView as ListView
                                     }

            listView.adapter = adapter.update(store.state.videos)
            listView.setOnItemClickListener { parent, view, position, id ->
                //TODO Play
            }

            val swipeRefresh2: SwipeRefreshLayout = if(swipeRefreshLayout == null){
                                                        swipeRefreshLayout = (withId(R.id.swipe_refresh){} as SwipeRefreshLayout)
                                                        swipeRefreshLayout as SwipeRefreshLayout
                                                   }else{
                                                        swipeRefreshLayout as SwipeRefreshLayout
                                                   }
            swipeRefresh2.isRefreshing = store.state.loading
            swipeRefresh2.setOnRefreshListener {
                store.dispatch(LOAD())
            }

            withId(R.id.list_view){
                adapter(adapter.update(store.state.videos))
            }

            withId(R.id.swipe_refresh){
                refreshing(store.state.loading)
                onRefresh {
                    store.dispatch(LOAD())
                }
            }
        }*/

        //endregion

        //region DSL
        frameLayout {
            if(store.state.loading){
                progressBar {
                    layoutGravity(CENTER)

                    onClick{
                        store.dispatch(INIT())
                    }
                }
            }else{
                listView {
                    init{
                        size(FILL,FILL)
                    }

                    adapter(adapter.update(store.state.videos))
                }
            }
        }

        //endregion
    }
}