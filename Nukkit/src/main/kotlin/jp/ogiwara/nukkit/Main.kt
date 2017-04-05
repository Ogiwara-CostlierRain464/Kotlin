package jp.ogiwara.nukkit

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.plugin.PluginBase

class Main : PluginBase(), Listener{

    override fun onEnable() {
        super.onEnable()
        logger.info("Hello, Kotlin")
        server.pluginManager.registerEvents(this,this)
    }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        e.player.sendMessage("Your server si using kotlin.")
    }
}