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


//Try One-way-Data-Binding
class SubscribeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSubscribeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_subscribe,container,false)
        val root = binding.root

        val state = State()

        binding.state = state

        val progressBar = root.findViewById(R.id.progress_bar) as ProgressBar
        progressBar.setOnClickListener {
            state.loading = true
        }

        return root
    }
}