package jp.ogiwara.nukkit

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.plugin.PluginBase
import jp.ogiwara.cycle.Store


class IdChecker: PluginBase() {

    private val store = Store(DispatcherImpl())

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        //guard
        if(sender !is Player){
            sender.sendMessage("Only player can execute this command")
            return true
        }

        store.dispatch(IdCheck(sender))

        return true
    }
}