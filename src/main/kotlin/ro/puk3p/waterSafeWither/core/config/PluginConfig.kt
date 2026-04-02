package ro.puk3p.waterSafeWither.core.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.Plugin

data class PluginConfig(
    val preventWaterBreak: Boolean,
    val preventWaterFlow: Boolean,
    val dropSpawners: Boolean,
    val spawnerFloatUp: Boolean,
    val floatVelocityY: Double,
    val witherFlowRadius: Double,
    val spawnerDropChance: Double,
) {
    companion object {
        fun load(plugin: Plugin): PluginConfig {
            plugin.saveDefaultConfig()
            plugin.reloadConfig()
            val c: FileConfiguration = plugin.config

            return PluginConfig(
                preventWaterBreak = c.getBoolean("features.prevent-water-break", true),
                preventWaterFlow = c.getBoolean("features.prevent-water-flow", false),
                dropSpawners = c.getBoolean("features.drop-spawners", true),
                spawnerFloatUp = c.getBoolean("features.spawner-float-up", true),
                floatVelocityY = c.getDouble("tuning.spawner-float-velocity-y", 0.35),
                witherFlowRadius = c.getDouble("tuning.wither-flow-radius", 20.0),
                spawnerDropChance = c.getDouble("tuning.spawner-drop-chance", 0.5),
            )
        }
    }
}
