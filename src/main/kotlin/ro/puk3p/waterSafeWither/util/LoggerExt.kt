package ro.puk3p.waterSafeWither.util

import org.bukkit.plugin.Plugin
import java.util.logging.Logger

val wswLogger: Logger = Logger.getLogger("WaterSafeWither")

fun Plugin.logInfo(msg: String) = logger.info("[WaterSafeWither] $msg")
fun Plugin.logWarn(msg: String) = logger.warning("[WaterSafeWither] $msg")