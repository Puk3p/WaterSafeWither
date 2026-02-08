package ro.puk3p.waterSafeWither.domain.policy

import org.bukkit.block.Block
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter

class DefaultWaterBlockPolicy(
    private val materials: BukkitMaterialAdapter
) : WaterBlockPolicy {

    override fun isWater(block: Block): Boolean = materials.isWater(block)
}
