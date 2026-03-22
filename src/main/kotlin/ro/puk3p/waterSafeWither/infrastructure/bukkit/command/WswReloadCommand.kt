package ro.puk3p.waterSafeWither.infrastructure.bukkit.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ro.puk3p.waterSafeWither.core.bootstrap.PluginBootstrap

class WswReloadCommand(
    private val bootstrap: PluginBootstrap
) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("watersafewither.reload")) {
            sender.sendMessage("§cNo permission.")
            return true
        }

        bootstrap.reload()
        sender.sendMessage("§aWaterSafeWither config reloaded.")
        return true
    }
}
