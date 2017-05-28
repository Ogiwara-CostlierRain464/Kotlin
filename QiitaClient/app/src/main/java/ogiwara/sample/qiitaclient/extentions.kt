package ogiwara.sample.qiitaclient

import android.support.annotation.IdRes
import android.view.View

//こういうファイル名にする文化?
fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}
