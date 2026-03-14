package ro.puk3p.waterSafeWither.application.service

import org.bukkit.block.Block
import org.bukkit.entity.EntityType
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
            return false
        }
        if (!waterPolicy.isWater(from)) {
            return false
        }

        val radius = config.witherFlowRadius
        val nearby = from.world.getNearbyEntities(from.location, radius, radius, radius)
        val witherNearby = nearby.any { it.type == EntityType.WITHER }

        if (witherNearby) {
            wswLogger.info("[FlowService] Water flow cancelled near Wither at ${from.x},${from.y},${from.z}")
        }
        return witherNearby
    }
}
