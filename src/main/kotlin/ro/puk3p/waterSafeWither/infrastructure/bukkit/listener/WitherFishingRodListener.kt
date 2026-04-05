package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent

class WitherFishingRodListener : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onPlayerFish(event: PlayerFishEvent) {
        val caught = event.caught ?: return
        if (caught.type == EntityType.WITHER) {
            event.isCancelled = true
        }
    }
}
