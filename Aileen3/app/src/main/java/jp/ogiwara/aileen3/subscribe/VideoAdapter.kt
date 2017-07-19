package jp.ogiwara.aileen3.subscribe

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.databinding.ViewRowVideoListBinding
import jp.ogiwara.aileen3.other.model.Video
import kotlin.properties.Delegates


class VideoAdapter(c: Context,videoArrayList: ArrayList<Video>): ArrayAdapter<Video>(c,0,videoArrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var binding: ViewRowVideoListBinding by Delegates.notNull()

        if(convertView == null){
            val inflater = LayoutInflater.from(context)
            binding = DataBindingUtil.inflate(inflater, R.layout.view_row_video_list,parent,false)

            convertView = binding.root
            convertView.setTag(binding)
        }else{
            binding = convertView.getTag() as ViewRowVideoListBinding
        }

        binding?.video = getItem(position)

        return binding.root
    }

}