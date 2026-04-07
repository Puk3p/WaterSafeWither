package ro.puk3p.waterSafeWither.core.bootstrap

import org.bukkit.plugin.Plugin
import ro.puk3p.waterSafeWither.application.service.SpawnerDropService
import ro.puk3p.waterSafeWither.application.service.WaterFlowPreventionService
import ro.puk3p.waterSafeWither.application.service.WitherBlockBreakService
import ro.puk3p.waterSafeWither.application.service.WitherExplosionService
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.DefaultWaterBlockPolicy
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter
import ro.puk3p.waterSafeWither.infrastructure.bukkit.listener.WaterFlowListener
import ro.puk3p.waterSafeWither.infrastructure.bukkit.listener.WitherBlockBreakListener
import ro.puk3p.waterSafeWither.infrastructure.bukkit.listener.WitherExplodeListener
import ro.puk3p.waterSafeWither.infrastructure.bukkit.listener.WitherFishingRodListener
import ro.puk3p.waterSafeWither.util.logInfo

class PluginBootstrap(private val plugin: Plugin) {
    private lateinit var config: PluginConfig

    private lateinit var spawnerDropService: SpawnerDropService
    private lateinit var witherBlockBreakService: WitherBlockBreakService
    private lateinit var witherExplosionService: WitherExplosionService
    private lateinit var waterFlowService: WaterFlowPreventionService

    fun start() {
        config = PluginConfig.load(plugin)

        val materials = BukkitMaterialAdapter()
        val waterPolicy = DefaultWaterBlockPolicy(materials)

        spawnerDropService = SpawnerDropService(config, materials)
        witherBlockBreakService = WitherBlockBreakService(config, waterPolicy, materials, spawnerDropService)
        witherExplosionService = WitherExplosionService(config, waterPolicy, materials, spawnerDropService)
        waterFlowService = WaterFlowPreventionService(config, waterPolicy)

        val pm = plugin.server.pluginManager
        pm.registerEvents(WitherBlockBreakListener(witherBlockBreakService), plugin)
        pm.registerEvents(WitherExplodeListener(witherExplosionService), plugin)
        pm.registerEvents(WaterFlowListener(waterFlowService), plugin)
        pm.registerEvents(WitherFishingRodListener(), plugin)

        plugin.logInfo(
            "Started. preventWaterBreak=${config.preventWaterBreak}, " +
                "preventWaterFlow=${config.preventWaterFlow}, " +
                "dropSpawners=${config.dropSpawners}, " +
                "spawnerFloatUp=${config.spawnerFloatUp}",
        )
    }

    fun reload() {
        val newConfig = PluginConfig.load(plugin)
        this.config = newConfig

        spawnerDropService.updateConfig(newConfig)
        witherBlockBreakService.updateConfig(newConfig)
        witherExplosionService.updateConfig(newConfig)
        waterFlowService.updateConfig(newConfig)

        plugin.logInfo(
            "Reloaded config. preventWaterBreak=${newConfig.preventWaterBreak}, " +
                "preventWaterFlow=${newConfig.preventWaterFlow}, " +
                "dropSpawners=${newConfig.dropSpawners}, " +
                "spawnerFloatUp=${newConfig.spawnerFloatUp}",
        )
    }
}
