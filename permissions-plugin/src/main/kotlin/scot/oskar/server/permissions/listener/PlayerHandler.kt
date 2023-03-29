package scot.oskar.server.permissions.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import scot.oskar.server.api.extension.toMiniMessage
import scot.oskar.server.permissions.data.PermissionPlayer
import scot.oskar.server.permissions.service.PermissionsService
import java.time.Instant
class PlayerHandler(private val permissionsService: PermissionsService): Listener {

    @EventHandler
    private fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        permissionsService.getPermissionPlayer(player.uniqueId).thenAccept { permissionPlayer ->
            if (permissionPlayer == null) {
                permissionsService.createPermissionPlayer(
                    PermissionPlayer(
                        player.uniqueId,
                        player.name,
                        "default",
                        emptySet()
                    )
                )
            } else {
                permissionsService.updatePermissionPlayer(permissionPlayer.copy(name = player.name))
            }
        }.exceptionally {
                throwable ->
                player.sendMessage("<red>Failed to load permission data, please report this issue. <dark_gray>(${Instant.now()})".toMiniMessage())
                throwable.printStackTrace()
                null
        }
    }

    @EventHandler
    private fun onQuit(event: PlayerJoinEvent) {
        val player = event.player
        permissionsService.getPermissionPlayer(player.uniqueId).thenAccept { permissionsService.updatePermissionPlayer(it!!) } // Not null because we just got it
        permissionsService.cache.invalidate(player.uniqueId)
    }
}