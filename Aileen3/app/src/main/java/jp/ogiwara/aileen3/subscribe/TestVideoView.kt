package jp.ogiwara.aileen3.subscribe

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.ViewRowVideoListBinding
import jp.ogiwara.aileen3.other.model.Video

class TestVideoView: FrameLayout{

    lateinit var binding: ViewRowVideoListBinding

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

    /*val image: ImageView by lazy{
        binding.root.findViewById(R.id.video_image) as ImageView
    }

    val title: TextView by lazy{
        binding.root.findViewById(R.id.video_title) as TextView
    }

    val viewCount: TextView by lazy{
        binding.root.findViewById(R.id.video_view_count) as TextView
    }*/

    init {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.view_row_video_list,this,false)
    }

    fun setVideo(video: Video){
        binding.video = video
    }

}