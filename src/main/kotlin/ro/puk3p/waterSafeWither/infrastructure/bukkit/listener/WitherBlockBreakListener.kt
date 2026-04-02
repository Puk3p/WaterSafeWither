package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent
import ro.puk3p.waterSafeWither.application.service.WitherBlockBreakService

class WitherBlockBreakListener(
    private val service: WitherBlockBreakService,
) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onEntityChangeBlock(event: EntityChangeBlockEvent) {
        val entity = event.entity ?: return
        val block = event.block

        if (service.handleBlockBreak(entity.type.name, block)) {
            event.isCancelled = true
        }
    }
}
