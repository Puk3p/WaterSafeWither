package ro.puk3p.waterSafeWither.application.service

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import ro.puk3p.waterSafeWither.core.config.PluginConfig
import ro.puk3p.waterSafeWither.infrastructure.bukkit.adapter.BukkitMaterialAdapter
import ro.puk3p.waterSafeWither.util.wswLogger

class SpawnerDropService(
    private var config: PluginConfig,
    private val materials: BukkitMaterialAdapter
) {
    fun updateConfig(newConfig: PluginConfig) {
        config = newConfig
    }

    fun handleSpawnerBreak(block: Block) {
        if (!config.dropSpawners) {
            wswLogger.info("[SpawnerDrop] dropSpawners is disabled, skipping")
            return
        }

        val loc = block.location
        wswLogger.info("[SpawnerDrop] Dropping spawner at ${loc.blockX},${loc.blockY},${loc.blockZ}")

        block.type = Material.AIR

        val drop = ItemStack(Material.MOB_SPAWNER, 1)
        val dropLoc = loc.add(0.5, 0.5, 0.5)
        val item = block.world.dropItemNaturally(dropLoc, drop)

        if (config.spawnerFloatUp) {
            val above = block.getRelative(0, 1, 0)
            if (materials.isWater(above)) {
                item.velocity = Vector(0.0, config.floatVelocityY, 0.0)
                wswLogger.info("[SpawnerDrop] Spawner floating up with velocity ${config.floatVelocityY}")
            }
        }
    }
}
