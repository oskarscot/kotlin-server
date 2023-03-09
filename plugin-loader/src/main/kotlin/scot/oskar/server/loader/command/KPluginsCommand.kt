package scot.oskar.server.loader.command

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.loader.PluginLoader

class KPluginsCommand(private val pluginLoader: PluginLoader) {

    @Command("loader plugins")
    fun plugins(sender: Player) {
        sender.sendMessage("${ChatColor.GOLD} KPlugins: ${ChatColor.GREEN} ${pluginLoader.plugins.joinToString(", ") { it.javaClass.getDeclaredAnnotation(PluginInfo::class.java).name }}")
    }
}