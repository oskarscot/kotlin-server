package scot.oskar.server.api

import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler

abstract class KPlugin(val plugin: JavaPlugin, val commandHandler: BukkitCommandHandler) {
    abstract fun enable();

    abstract fun disable();

}