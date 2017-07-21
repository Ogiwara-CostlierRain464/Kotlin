package jp.ogiwara.aileen3.test1

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.aileen3.other.model.Video


internal data class State(var videos: ObservableArrayList<Video> = ObservableArrayList(),
                          var loading: ObservableBoolean = ObservableBoolean(false))