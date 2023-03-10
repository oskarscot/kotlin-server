package scot.oskar.server.loader.command

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import revxrsal.commands.annotation.Command
import revxrsal.commands.bukkit.annotation.CommandPermission
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.api.extension.toMiniMessage
import scot.oskar.server.loader.PluginLoader

class KPluginsCommand(private val pluginLoader: PluginLoader) {

    //TODO: Fix this class, implement proper message handling and configuration

    @CommandPermission("kloader.plugins")
    @Command("loader plugins")
    fun plugins(sender: Player) {
        sender.sendMessage("<gold>KPlugins: <green>${pluginLoader.plugins.joinToString(", ") { it.javaClass.getDeclaredAnnotation(PluginInfo::class.java).name }}".toMiniMessage())
    }

    @Command("loader reload")
    @CommandPermission("kloader.reload")
    fun reload(sender: Player) {
        pluginLoader.plugins.forEach { it.disable() }
        pluginLoader.plugins.clear()
        pluginLoader.commandHandler.unregisterAllCommands()
        HandlerList.unregisterAll(pluginLoader)
        pluginLoader.loadPlugins()
        sender.sendMessage("<gold>KPlugins: <green>Reloaded".toMiniMessage())
    }
}