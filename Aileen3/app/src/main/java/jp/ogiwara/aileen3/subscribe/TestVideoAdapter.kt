package jp.ogiwara.aileen3.subscribe

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import jp.ogiwara.aileen3.other.model.Video

class TestVideoAdapter(private val context: Context): BaseAdapter() {

    var videos: List<Video> = emptyList()

    override fun getCount() = videos.size

    override fun getItem(position: Int) = videos.get(position)

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
        ((convertView as? TestVideoView) ?: TestVideoView(context)).apply{
            setVideo(videos[position])
        }

    fun update(newVideos: List<Video>): TestVideoAdapter{
        if(videos != newVideos){
            videos = newVideos
            notifyDataSetChanged()
        }

        return this
    }
}