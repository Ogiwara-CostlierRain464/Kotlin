package jp.ogiwara.aileen3.subscribe

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.FragmentSubscribeBinding
import jp.ogiwara.aileen3.other.model.Video
import jp.ogiwara.kotlin.cicle.BasicStore

internal val state = State()
internal val store = BasicStore(state, reducer)
//Try One-way-Data-Binding
class SubscribeFragment: Fragment(),TestEventHandler{

    val videoList = ObservableArrayList<Video>()

    //Write view at here?
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSubscribeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_subscribe,container,false)
        val root = binding.root
        binding.state = state
        binding.handlers = this

        //View
        val swipeRefresh = root.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener {
            store.dispatch(LOAD())
        }

        return root
    }

    override fun onAdd(view: View) {
        //Nothing to do
    }

    override fun onDelete(view: View) {
        //Nothing to do
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        //TODO Play
    }
}