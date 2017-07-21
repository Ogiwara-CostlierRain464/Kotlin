package jp.ogiwara.aileen3.test1

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.aileen3.other.model.Video


class Test1ListView(c: Context,attrs: AttributeSet): ListView(c,attrs){

    var adapter: Test1VideoAdapter? = null

    fun setList(videoList: ObservableArrayList<Video>){
        if(adapter == null){
            adapter = Test1VideoAdapter(context,videoList)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }

}