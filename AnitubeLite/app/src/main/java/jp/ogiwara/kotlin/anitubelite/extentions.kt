package jp.ogiwara.kotlin.anitubelite

import android.support.annotation.IdRes
import android.view.View

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}
