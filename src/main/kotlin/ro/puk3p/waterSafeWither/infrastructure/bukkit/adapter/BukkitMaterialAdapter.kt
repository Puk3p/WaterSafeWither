package ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter

import org.bukkit.Material
import org.bukkit.block.Block

class BukkitMaterialAdapter {

    fun isWater(block: Block): Boolean {
        val t = block.type
        return t == Material.WATER || t == Material.STATIONARY_WATER
    }

    fun isSpawner(block: Block): Boolean {
        return block.type == Material.MOB_SPAWNER
    }
}