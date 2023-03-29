package scot.oskar.server.permissions

import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.permissions.listener.PlayerJoinHandler
import scot.oskar.server.permissions.service.PermissionsService

@PluginInfo(
    name = "permissions-plugin",
    version = "0.0.1",
    description = "Permission plugin",
    authors = ["oskarscot"]
)
class PermissionPlugin(plugin: JavaPlugin, commandHandler: BukkitCommandHandler) : KPlugin(plugin, commandHandler) {

    private lateinit var permissionsService: PermissionsService

    override fun enable() {
        permissionsService = PermissionsService(connection!!)
        registerListeners(PlayerJoinHandler(permissionsService))
    }

    override fun disable() {
    }

}