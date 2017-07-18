package jp.ogiwara.aileen3.subscribe

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.FragmentSubscribeBinding
import jp.ogiwara.kotlin.cicle.BasicStore

//Try One-way-Data-Binding
class SubscribeFragment: Fragment() {

    //Write view at here?
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSubscribeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_subscribe,container,false)
        val root = binding.root

        val state = State()

        binding.state = state
        val store = BasicStore(state, reducer)

        val progressBar = root.findViewById(R.id.progress_bar) as ProgressBar
        progressBar.setOnClickListener {
            store.dispatch(TOUCH())
        }

        return root
    }
}