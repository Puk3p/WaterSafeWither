package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.domain.policy.WaterBlockPolicy
import ro.puk3p.waterSafeWither.util.wswLogger

class WaterFlowPreventionService(
    private var config: PluginConfig,
    private val waterPolicy: WaterBlockPolicy
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun shouldCancelFlow(from: Block): Boolean {
        if (!config.preventWaterFlow) {
            wswLogger.info("[FlowService] preventWaterFlow is disabled, allowing flow")
            return false
        }
        val isWater = waterPolicy.isWater(from)
        if (isWater) {
            wswLogger.info("[FlowService] Water flow detected from ${from.type} at ${from.x},${from.y},${from.z}, cancelling")
        }
        return isWater
    }
}
