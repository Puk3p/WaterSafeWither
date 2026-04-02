package ro.puk3p.waterSafeWither.domain.policy

import org.bukkit.block.Block

interface WaterBlockPolicy {
    fun isWater(block: Block): Boolean
}
