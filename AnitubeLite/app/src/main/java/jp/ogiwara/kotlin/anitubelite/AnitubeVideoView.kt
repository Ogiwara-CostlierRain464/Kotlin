package jp.ogiwara.kotlin.anitubelite

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import jp.ogiwara.kotlin.anitubeapi.model.Video

class AnitubeVideoView: FrameLayout {

    constructor(context: Context?): super(context)

    constructor(context: Context?,
                attrs: AttributeSet?): super(context,attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int): super(context,attrs,defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)


    val image: ImageView by bindView(R.id.video_image)

    val title: TextView by bindView(R.id.video_title)

    val viewCount: TextView by bindView(R.id.video_view_count)

    init{
        LayoutInflater.from(context).inflate(R.layout.anitube_video_view,this)
    }

    fun setVideo(video: Video){
        Glide.with(context).load(video.imgUrl).into(image)
        title.text = video.title
        viewCount.text = video.views
    }
}