package jp.ogiwara.aileen3.topchart

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brianegan.bansa.BaseStore

val store = BaseStore(State(), reducer)

class TopChartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return RootView(container!!.context)
    }
}