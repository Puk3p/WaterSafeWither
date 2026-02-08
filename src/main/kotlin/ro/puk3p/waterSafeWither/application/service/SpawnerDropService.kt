package ro.puk3p.waterSafeWither.application.service

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter

class SpawnerDropService(
    private var config: PluginConfig,
    private val materials: BukkitMaterialAdapter
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun handleSpawnerBreak(block: Block) {
        if (!config.dropSpawners) return

        block.type = Material.AIR

        val drop = ItemStack(Material.MOB_SPAWNER, 1)
        val loc = block.location.add(0.5, 0.5, 0.5)
        val item = block.world.dropItemNaturally(loc, drop)

        if (config.spawnerFloatUp) {
            val above = block.getRelative(0, 1, 0)
            if (materials.isWater(above)) {
                item.velocity = Vector(0.0, config.floatVelocityY, 0.0)
            }
        }
    }
}
