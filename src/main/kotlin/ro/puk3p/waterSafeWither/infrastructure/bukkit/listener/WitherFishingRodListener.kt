package ro.puk3p.waterSafeWither.infrastructure.bukkit.listener

import org.bukkit.entity.EntityType
import org.bukkit.entity.FishHook
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerFishEvent

class WitherFishingRodListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onPlayerFish(event: PlayerFishEvent) {
        if (event.state != PlayerFishEvent.State.CAUGHT_ENTITY) {
            return
        }

        val caught = event.caught ?: return
        if (caught.type == EntityType.WITHER) {
            event.isCancelled = true
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        if (event.entity.type != EntityType.WITHER) {
            return
        }

        if (event.damager is FishHook) {
            event.isCancelled = true
            event.damage = 0.0
        }
    }
}
