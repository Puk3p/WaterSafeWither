package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent
import ro.puk3p.waterSafeWither.application.service.WitherBlockBreakService
import ro.puk3p.waterSafeWither.util.wswLogger

class WitherBlockBreakListener(
    private val service: WitherBlockBreakService
) : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onEntityChangeBlock(event: EntityChangeBlockEvent) {
        val entity = event.entity ?: return
        val block = event.block

        wswLogger.info("[BlockBreak] EntityChangeBlockEvent fired: entity=${entity.type.name}, block=${block.type} at ${block.x},${block.y},${block.z}")

        if (service.shouldCancelBlockBreak(entity.type.name, block)) {
            event.isCancelled = true
            wswLogger.info("[BlockBreak] Cancelled Wither body breaking water at ${block.x},${block.y},${block.z}")
        }
    }
}
