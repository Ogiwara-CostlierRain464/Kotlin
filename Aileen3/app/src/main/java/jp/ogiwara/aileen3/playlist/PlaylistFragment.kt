package jp.ogiwara.aileen3.playlist

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.FragmentPlaylistBinding


class PlaylistFragment : Fragment(),MainEventHandler{

    val dateTimeList = ObservableArrayList<DateTime>().apply {
        add(DateTime())
        add(DateTime())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPlaylistBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_playlist,container,false)
        val root = binding.root

        binding.dateTimeList = dateTimeList
        binding.handlers = this

        return root
    }

    override fun onAdd(view: View?) {
        dateTimeList.add(DateTime())
    }

    override fun onDelete(view: View?) {
        if(dateTimeList.size > 0){
            dateTimeList.removeAt(dateTimeList.size - 1)
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(context,"Click",Toast.LENGTH_SHORT).show()
    }
}