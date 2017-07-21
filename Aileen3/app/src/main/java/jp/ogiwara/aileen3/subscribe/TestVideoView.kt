package jp.ogiwara.aileen3.subscribe

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableInt
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.ViewRowVideoListBinding
import jp.ogiwara.aileen3.other.model.Video
import jp.ogiwara.kotlin.cicle.Action
import jp.ogiwara.kotlin.cicle.BasicStore
import jp.ogiwara.kotlin.cicle.Reducer
import org.jetbrains.anko.find

class TestVideoView: FrameLayout{

    var binding: ViewRowVideoListBinding? = null

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

    //todo ステート管理
    //ステート管理そんなに大事? -> 電卓機能つけてみる

    data class State(var sum: ObservableInt = ObservableInt(0))

    data class EDIT(val num: Int): Action

    val reducer = object: Reducer<State>{
        override fun reduce(state: State, action: Action) {
            when(action){
                is EDIT -> {
                    state.sum.set(1 + action.num)
                }
            }
        }
    }

    init {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.view_row_video_list,this,true)//true大事!
        val state = State()
        binding?.state = state
        val store = BasicStore(state,reducer)
        val editText = binding?.root?.find<EditText>(R.id.edit_text)
        editText!!.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = editText.text.toString()
                if(text != ""){
                    store.dispatch(EDIT(editText.text.toString().toInt()))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    fun setVideo(video: Video){
        //binding?.video = video
        //val image = binding?.root?.findViewById(R.id.video_image) as ImageView
        //Glide.with(context).load(video.thumbnailUrl).into(image)
    }
}