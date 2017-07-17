package jp.ogiwara.aileen3

import android.support.annotation.IdRes
import android.util.Log
import android.view.View
import org.jetbrains.anko.find
import kotlin.reflect.KClass

/**
 * 拡張メソッドの一覧
 */
fun i(log: String,`class`: KClass<*> = App::class){
    Log.i(`class`.simpleName,log)
}

fun String.cutEnd(): String{
    if(length <= 1){
        return this
    }
    return this.substring(0,length-1)
}

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}
