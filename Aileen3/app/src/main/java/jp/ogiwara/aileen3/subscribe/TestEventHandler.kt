package jp.ogiwara.aileen3.subscribe

import android.view.View
import android.widget.AdapterView


interface TestEventHandler {
    fun onAdd(view: View)
    fun onDelete(view: View)
    fun onItemClick(parent: AdapterView<*>,view: View,position: Int,id: Long)
}