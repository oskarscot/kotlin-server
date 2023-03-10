package scot.oskar.server.loader.command

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import revxrsal.commands.annotation.Command
import revxrsal.commands.bukkit.annotation.CommandPermission
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.loader.PluginLoader

class KPluginsCommand(private val pluginLoader: PluginLoader) {

    @CommandPermission("kloader.plugins")
    @Command("loader plugins")
    fun plugins(sender: Player) {
        sender.sendMessage("${ChatColor.GOLD} KPlugins: ${ChatColor.GREEN} ${pluginLoader.plugins.joinToString(", ") { it.javaClass.getDeclaredAnnotation(PluginInfo::class.java).name }}")
    }

    @Command("loader reload")
    @CommandPermission("kloader.reload")
    fun reload(sender: Player) {
        pluginLoader.plugins.forEach { it.disable() }
        pluginLoader.plugins.clear()
        pluginLoader.commandHandler.unregisterAllCommands()
        HandlerList.unregisterAll(pluginLoader)
        pluginLoader.loadPlugins()
        sender.sendMessage("${ChatColor.GOLD} KPlugins: ${ChatColor.GREEN} Reloaded")
    }
}