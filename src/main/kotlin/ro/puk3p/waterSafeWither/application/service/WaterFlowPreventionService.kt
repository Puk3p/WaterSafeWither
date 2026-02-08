package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.WaterBlockPolicy

class WaterFlowPreventionService(
    private var config: PluginConfig,
    private val waterPolicy: WaterBlockPolicy
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun shouldCancelFlow(from: Block): Boolean {
        if (!config.preventWaterFlow) return false
        return waterPolicy.isWater(from)
    }
}
