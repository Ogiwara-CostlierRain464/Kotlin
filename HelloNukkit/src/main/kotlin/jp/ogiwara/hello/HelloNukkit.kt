package jp.ogiwara.hello

import cn.nukkit.plugin.PluginBase

//Why don't you use this as an event callback class?
class HelloNukkit: PluginBase(){
    override fun onEnable() {
        super.onEnable()
        logger.info("Hello Nukkit!")
    }
}