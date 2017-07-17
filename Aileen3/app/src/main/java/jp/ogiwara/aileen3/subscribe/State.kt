package jp.ogiwara.aileen3.subscribe

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import jp.ogiwara.aileen3.other.model.Video

internal data class State(var videos: ObservableField<List<Video>> = ObservableField<List<Video>>(),
                          var loading: ObservableBoolean = ObservableBoolean(false))

internal data class State2(var vidoes: List<Video> = arrayListOf(),
                           var loading: Boolean = false): BaseObservable()