package jp.ogiwara.aileen3.test1

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.FragmentTest1Binding
import jp.ogiwara.kotlin.cicle.BasicStore
import org.jetbrains.anko.find
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView

private val state = State()
internal val store = BasicStore(state, reducer)

class Test1Fragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTest1Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test1,container,false)
        val root = binding.root
        binding.state = state

        val swipeRefresh = root.find<SwipeRefreshLayout>(R.id.swipe_refresh)
        val listView = root.find<Test1ListView>(R.id.test1_list_view)

        swipeRefresh.setOnRefreshListener {
            store.dispatch(LOAD())
        }

        listView.addFooterView(object : RenderableView(context){
            override fun view() {
                progressBar {
                    gravity(CENTER_VERTICAL)
                }
            }
        })
        listView.setOnScrollListener(object : AbsListView.OnScrollListener{
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if((totalItemCount - visibleItemCount) == firstVisibleItem){//最初とスクロール完了したとき
                    store.dispatch(LOAD())
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) = Unit
        })

        //Init
        //store.dispatch(LOAD())

        return root
    }

}