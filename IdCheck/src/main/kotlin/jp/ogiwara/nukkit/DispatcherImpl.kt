package jp.ogiwara.nukkit

import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.Dispatcher


class DispatcherImpl : Dispatcher{
    override fun dispatch(action: Action) {
        when(action){
            is IdCheck -> {
                val item = action.player.inventory.itemInHand
                action.player.sendMessage("You're holding ${item?.name ?: "Nothing"}, ID: ${item?.id ?: "None"}")
            }
        }
    }
}