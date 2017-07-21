package jp.ogiwara.aileen3.test1

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.aileen3.other.model.Video

class Test1VideoAdapter(c: Context,videoArrayList: ArrayList<Video>): ArrayAdapter<Video>(c,0,videoArrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            ((convertView as? Test1VideoView) ?: Test1VideoView(context)).apply {
                setVideo(getItem(position))
            }
}