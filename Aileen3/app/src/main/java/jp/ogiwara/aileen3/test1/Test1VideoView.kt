package jp.ogiwara.aileen3.test1

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.Test1VideoViewBinding
import jp.ogiwara.aileen3.other.model.Video
import org.jetbrains.anko.find


class Test1VideoView: FrameLayout{

    var binding: Test1VideoViewBinding? = null

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

    init {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.test1_video_view,this,true)
    }

    fun setVideo(video: Video){
        binding?.video = video
        val imageView = binding!!.root.find<ImageView>(R.id.video_image)
        Glide.with(context).load(video.thumbnailUrl).into(imageView)
    }
}