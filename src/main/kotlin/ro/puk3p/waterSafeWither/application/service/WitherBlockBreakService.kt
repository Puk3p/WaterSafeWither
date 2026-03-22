package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.WaterBlockPolicy
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter

class WitherBlockBreakService(
    private var config: PluginConfig,
    private val waterPolicy: WaterBlockPolicy,
    private val materials: BukkitMaterialAdapter,
    private val spawnerDropService: SpawnerDropService
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun handleBlockBreak(entityTypeName: String, block: Block): Boolean {
        if (entityTypeName != "WITHER") {
            return false
        }

        if (config.preventWaterBreak && waterPolicy.isWater(block)) {
            return true
        }

        if (config.dropSpawners && materials.isSpawner(block)) {
            spawnerDropService.handleSpawnerBreak(block)
            return true
        }

        return false
    }
}
