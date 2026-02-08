package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.WaterBlockPolicy
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter

class WitherExplosionService(
    private var config: PluginConfig,
    private val waterPolicy: WaterBlockPolicy,
    private val materials: BukkitMaterialAdapter,
    private val spawnerDropService: SpawnerDropService
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun handleWitherExplosion(entityTypeName: String, blocks: MutableList<Block>) {
        val isWitherRelated =
            entityTypeName == "WITHER" || entityTypeName == "WITHER_SKULL"
        if (!isWitherRelated) return

        if (config.preventWaterBreak) {
            blocks.removeAll { waterPolicy.isWater(it) }
        }

        if (config.dropSpawners) {
            val spawners = blocks.filter { materials.isSpawner(it) }
            if (spawners.isNotEmpty()) {
                blocks.removeAll(spawners)
                spawners.forEach { spawnerDropService.handleSpawnerBreak(it) }
            }
        }
    }
}
