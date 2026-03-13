package ro.puk3p.waterSafeWither.infrastructure.bukkit.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ro.puk3p.waterSafeWither.core.bootstrap.PluginBootstrap
import ro.puk3p.waterSafeWither.util.wswLogger

class WswReloadCommand(
    private val bootstrap: PluginBootstrap
) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        wswLogger.info("[Command] /wswreload executed by ${sender.name}")

        if (!sender.hasPermission("watersafewither.reload")) {
            wswLogger.info("[Command] ${sender.name} denied - no permission")
            sender.sendMessage("§cNo permission.")
            return true
        }

        bootstrap.reload()
        wswLogger.info("[Command] Config reloaded successfully by ${sender.name}")
        sender.sendMessage("§aWaterSafeWither config reloaded.")
        return true
    }
}
