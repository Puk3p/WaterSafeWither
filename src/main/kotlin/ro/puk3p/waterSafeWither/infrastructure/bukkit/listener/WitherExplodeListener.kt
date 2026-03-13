package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import ro.puk3p.waterSafeWither.application.service.WitherExplosionService
import ro.puk3p.waterSafeWither.util.wswLogger

class WitherExplodeListener(
    private val service: WitherExplosionService
) : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onEntityExplode(event : EntityExplodeEvent) {
        val entity = event.entity ?: return

        wswLogger.info("[Explode] EntityExplodeEvent fired: entity=${entity.type.name}, blocks=${event.blockList().size} at ${entity.location.blockX},${entity.location.blockY},${entity.location.blockZ}")
        service.handleWitherExplosion(entity.type.name, event.blockList())
    }
}