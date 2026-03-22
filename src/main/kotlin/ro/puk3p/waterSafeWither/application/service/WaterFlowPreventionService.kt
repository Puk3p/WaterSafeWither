package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import org.bukkit.entity.Wither
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
        if (!config.preventWaterFlow) {
            return false
        }
        if (!waterPolicy.isWater(from)) {
            return false
        }

        val radius = config.witherFlowRadius
        val radiusSq = radius * radius
        val loc = from.location
        val withers = from.world.getEntitiesByClass(Wither::class.java)
        return withers.any { it.location.distanceSquared(loc) <= radiusSq }
    }
}
