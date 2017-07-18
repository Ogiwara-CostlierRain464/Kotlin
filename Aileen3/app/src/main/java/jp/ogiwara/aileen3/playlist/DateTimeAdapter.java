package jp.ogiwara.aileen3.playlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.ogiwara.aileen3.R;
import jp.ogiwara.aileen3.databinding.ViewRowDateTimeListBinding;

public class DateTimeAdapter extends ArrayAdapter<DateTime>{

    public DateTimeAdapter(Context context, ArrayList<DateTime> dateTimeArrayList){
        super(context,0,dateTimeArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewRowDateTimeListBinding binding;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.view_row_date_time_list,parent,false);

            convertView = binding.getRoot();
            convertView.setTag(binding);
        }else{
            binding = (ViewRowDateTimeListBinding) convertView.getTag();
        }

        binding.setDateTime(getItem(position));

        return binding.getRoot();
    }
}
