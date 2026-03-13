package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.WaterBlockPolicy
import ro.puk3p.waterSafeWither.util.wswLogger

class WitherBlockBreakService(
    private var config: PluginConfig,
    private val waterPolicy: WaterBlockPolicy
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun shouldCancelBlockBreak(entityTypeName: String, block: Block): Boolean {
        if (!config.preventWaterBreak) {
            wswLogger.info("[BlockBreakService] preventWaterBreak is disabled, allowing break")
            return false
        }
        if (entityTypeName != "WITHER") {
            wswLogger.info("[BlockBreakService] Entity is $entityTypeName, not WITHER, skipping")
            return false
        }
        val isWater = waterPolicy.isWater(block)
        wswLogger.info("[BlockBreakService] Block ${block.type} at ${block.x},${block.y},${block.z} isWater=$isWater")
        return isWater
    }
}
