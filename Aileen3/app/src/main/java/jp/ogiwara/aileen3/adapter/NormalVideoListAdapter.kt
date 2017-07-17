package jp.ogiwara.aileen3.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import jp.ogiwara.aileen3.customview.NormalVideoView
import jp.ogiwara.aileen3.other.model.Video


class NormalVideoListAdapter(private val context: Context): BaseAdapter() {

    var videos: List<Video> = emptyList()

    override fun getCount() = videos.size

    override fun getItem(position: Int) = videos.get(position)

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? NormalVideoView) ?: NormalVideoView(context)).apply {
                setVideo(videos[position])
            }

    fun update(newVideos: List<Video>): NormalVideoListAdapter{
        if(videos != newVideos){
            videos = newVideos
            notifyDataSetChanged()
        }

        return this
    }

}