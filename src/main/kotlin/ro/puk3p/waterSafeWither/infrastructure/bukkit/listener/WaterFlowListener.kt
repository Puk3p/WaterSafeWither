package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFromToEvent
import ro.puk3p.waterSafeWither.application.service.WaterFlowPreventionService
import ro.puk3p.waterSafeWither.util.wswLogger

class WaterFlowListener(
    private val service: WaterFlowPreventionService
) : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onWaterFlow(event: BlockFromToEvent) {
        val block = event.block
        if (service.shouldCancelFlow(block)) {
            event.isCancelled = true
            wswLogger.info("[WaterFlow] Cancelled water flow from ${block.type} at ${block.x},${block.y},${block.z}")
        }
    }
}
