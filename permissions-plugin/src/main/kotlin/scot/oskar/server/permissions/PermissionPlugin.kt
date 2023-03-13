package scot.oskar.server.permissions

import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo

@PluginInfo(
    name = "permissions-plugin",
    version = "0.0.1",
    description = "Permission plugin",
    authors = ["oskarscot"]
)
class PermissionPlugin(plugin: JavaPlugin, commandHandler: BukkitCommandHandler) : KPlugin(plugin, commandHandler) {

    override fun enable() {
        plugin.logger.info(connection.toString())
    }

    override fun disable() {
    }

}