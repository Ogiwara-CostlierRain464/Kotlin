package jp.ogiwara.aileen3.playlist;

import android.view.View;
import android.widget.AdapterView;

public interface MainEventHandler {
    void onAdd(View view);
    void onDelete(View view);
    void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
