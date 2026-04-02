package ro.puk3p.waterSafeWither.util

import org.bukkit.plugin.Plugin

fun Plugin.logInfo(msg: String) = logger.info("[WaterSafeWither] $msg")

fun Plugin.logWarn(msg: String) = logger.warning("[WaterSafeWither] $msg")
