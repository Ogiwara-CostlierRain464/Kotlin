package jp.ogiwara.aileen3.eventbus

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import jp.ogiwara.aileen3.base.TestEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) =
        RootView(container!!.context)

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    fun onEvent(event: TestEvent){
        if(event.target == EventBusFragment::class.simpleName){
            Toast.makeText(context,"EventBus 成功!",Toast.LENGTH_LONG).show()

            EventBus.getDefault().removeStickyEvent(TestEvent::class.java)
        }
    }
}