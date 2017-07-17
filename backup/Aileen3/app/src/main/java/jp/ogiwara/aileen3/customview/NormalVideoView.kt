package jp.ogiwara.aileen3.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.bindView
import jp.ogiwara.aileen3.other.model.Video
import trikita.anvil.Anvil

class NormalVideoView: FrameLayout {

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

    init {
        LayoutInflater.from(context).inflate(R.layout.normal_video_item,this)
    }

    fun setVideo(video: Video){
        Glide.with(context).load(video.thumbnailUrl).into(image)
        title?.text = video.title
        viewCount?.text = video.viewCount
    }
}

