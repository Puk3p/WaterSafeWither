package ro.puk3p.waterSafeWither

import org.bukkit.plugin.java.JavaPlugin
import ro.puk3p.waterSafeWither.core.bootstrap.PluginBootstrap
import ro.puk3p.waterSafeWither.infrastructure.bukkit.command.WswReloadCommand

class WaterSafeWither : JavaPlugin() {
    private lateinit var bootstrap: PluginBootstrap

    override fun onEnable() {
        bootstrap = PluginBootstrap(this)
        bootstrap.start()

        getCommand("wswreload")?.setExecutor(WswReloadCommand(bootstrap))

        logger.info("WaterSafeWither has been enabled!")
    }

    override fun onDisable() {
        logger.info("WaterSafeWither has been disabled!")
    }
}
