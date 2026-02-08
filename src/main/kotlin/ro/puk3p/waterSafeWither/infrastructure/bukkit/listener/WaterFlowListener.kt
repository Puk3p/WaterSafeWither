package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFromToEvent
import ro.puk3p.waterSafeWither.application.service.WaterFlowPreventionService

class WaterFlowListener(
    private val service: WaterFlowPreventionService
) : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onWaterFlow(event: BlockFromToEvent) {
        if (service.shouldCancelFlow(event.block)) {
            event.isCancelled = true
        }
    }
}
