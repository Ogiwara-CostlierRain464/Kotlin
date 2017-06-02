package ogiwara.sample.rxandroid

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.view.View
import android.widget.Toast

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun <T: View> Activity.find(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun Context.toast(text: String,length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,text,length)
}
