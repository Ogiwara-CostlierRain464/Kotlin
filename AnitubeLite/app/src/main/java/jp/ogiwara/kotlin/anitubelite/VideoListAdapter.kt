package jp.ogiwara.kotlin.anitubelite

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import jp.ogiwara.kotlin.anitubeapi.model.Video

class VideoListAdapter(private val context: Context): BaseAdapter() {

    var videos: List<Video> = emptyList()

    override fun getCount() = videos.size

    override fun getItem(position: Int) = videos.get(position)

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? AnitubeVideoView) ?: AnitubeVideoView(context)).apply {
                setVideo(videos.get(position))
            }

    fun update(newVideos: List<Video>): VideoListAdapter{

        if(videos != newVideos){
            this.videos = newVideos
            notifyDataSetChanged()
        }

        return this
    }
}