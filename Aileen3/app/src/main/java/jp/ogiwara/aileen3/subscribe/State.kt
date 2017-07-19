package jp.ogiwara.aileen3.subscribe

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import jp.ogiwara.aileen3.other.model.Video

internal data class State(var videos: ObservableArrayList<Video> = ObservableArrayList(),
                          var loading: ObservableBoolean = ObservableBoolean(false))