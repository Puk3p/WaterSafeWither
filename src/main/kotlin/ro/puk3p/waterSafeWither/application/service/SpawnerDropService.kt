package ro.puk3p.waterSafeWither.application.service

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.CreatureSpawner
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta
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

    private fun formatEntityName(name: String): String {
        return name.split("_").joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.uppercaseChar() }
        }
    }

    fun handleSpawnerBreak(block: Block) {
        if (!config.dropSpawners) {
            wswLogger.info("[SpawnerDrop] dropSpawners is disabled, skipping")
            return
        }

        val spawnerState = block.state as? CreatureSpawner
        val spawnedType = spawnerState?.spawnedType
        val loc = block.location

        wswLogger.info("[SpawnerDrop] Dropping spawner at ${loc.blockX},${loc.blockY},${loc.blockZ}, mobType=${spawnedType?.name ?: "UNKNOWN"}")

        block.type = Material.AIR

        val drop = ItemStack(Material.MOB_SPAWNER, 1)

        val meta = drop.itemMeta
        if (spawnedType != null) {
            if (meta is BlockStateMeta) {
                val freshSpawner = meta.blockState as? CreatureSpawner
                if (freshSpawner != null) {
                    freshSpawner.spawnedType = spawnedType
                    meta.blockState = freshSpawner
                    wswLogger.info("[SpawnerDrop] Preserved mob type: ${spawnedType.name}")
                }
            }
            val displayName = formatEntityName(spawnedType.name)
            meta.displayName = "\u00a7c$displayName \u00a7fSpawner"
            wswLogger.info("[SpawnerDrop] Set display name: §c$displayName §fSpawner")
        }
        drop.itemMeta = meta

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
