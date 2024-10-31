package com.mrvenumx.nukkit.plugins

import java.text.NumberFormat

import cn.nukkit.plugin.PluginBase
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import me.onebone.economyapi.EconomyAPI

class EconomyPlaceholder : PluginBase() {
    override fun onEnable() {
        super.onEnable()
        val papi = PlaceholderAPI.getInstance()

        with(server.pluginManager) {
            getPlugin("EconomyAPI")?.let {
                val eco = EconomyAPI.getInstance()

                logger.debug("EconomyAPI is loaded!")
                papi.build<String>("eco_money") {
                    autoUpdate(true)
                    visitorLoader {
                        NumberFormat.getInstance().format(eco.myMoney(player))
                    }
                }
            } ?: logger.warning("plugin EconomyAPI was not found, please install it!")

        }
    }
}