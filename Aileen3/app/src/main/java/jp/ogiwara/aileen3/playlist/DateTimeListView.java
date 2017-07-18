package jp.ogiwara.aileen3.playlist;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.util.AttributeSet;
import android.widget.ListView;

public class DateTimeListView extends ListView {

    DateTimeAdapter adapter;

    public DateTimeListView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public void setList(ObservableArrayList<DateTime> dateTimeList){
        if(getAdapter() == null){
            adapter = new DateTimeAdapter(getContext(),dateTimeList);
            setAdapter(adapter);
        }

        adapter.notifyDataSetChanged();
    }
}
