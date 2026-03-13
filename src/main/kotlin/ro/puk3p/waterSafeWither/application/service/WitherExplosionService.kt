package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.WaterBlockPolicy
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter
import ro.puk3p.waterSafeWither.util.wswLogger

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
        if (!isWitherRelated) {
            wswLogger.info("[ExplosionService] Entity $entityTypeName is not Wither-related, skipping")
            return
        }

        wswLogger.info("[ExplosionService] Handling Wither explosion from $entityTypeName, ${blocks.size} blocks affected")

        if (config.preventWaterBreak) {
            val waterCount = blocks.count { waterPolicy.isWater(it) }
            blocks.removeAll { waterPolicy.isWater(it) }
            wswLogger.info("[ExplosionService] Removed $waterCount water blocks from explosion list")
        } else {
            wswLogger.info("[ExplosionService] preventWaterBreak is disabled, water not protected")
        }

        if (config.dropSpawners) {
            val spawners = blocks.filter { materials.isSpawner(it) }
            if (spawners.isNotEmpty()) {
                wswLogger.info("[ExplosionService] Found ${spawners.size} spawner(s) to drop")
                blocks.removeAll(spawners)
                spawners.forEach { spawnerDropService.handleSpawnerBreak(it) }
            }
        } else {
            wswLogger.info("[ExplosionService] dropSpawners is disabled")
        }
    }
}
