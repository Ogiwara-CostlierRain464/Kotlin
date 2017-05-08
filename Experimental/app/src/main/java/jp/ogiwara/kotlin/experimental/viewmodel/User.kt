package jp.ogiwara.kotlin.experimental.viewmodel

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View

class User {

    val name = ObservableField<String>()
    val age = ObservableInt()
    val likes = ObservableInt()

    constructor(aName: String,aAge: Int){
        name.set(aName)
        age.set(aAge)
        likes.set(0)
    }

    fun onClickLike(v: View){
        likes.set(likes.get() + 1)
    }
}