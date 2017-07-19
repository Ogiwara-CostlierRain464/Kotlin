package jp.ogiwara.aileen3.subscribe

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.aileen3.other.model.Video


class VideoListView(c: Context,attrs: AttributeSet): ListView(c,attrs){

    var adapter: VideoAdapter? = null

    fun setList(videoList: ObservableArrayList<Video>){
        if(adapter == null){
            adapter = VideoAdapter(context,videoList)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }
}