package scot.oskar.server.hello.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import scot.oskar.server.hello.message.MessageService

class TestListener(private val service: MessageService): Listener {

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        service.viewerService.findOrCreateViewer(event.player)
        service.getMessage { it.welcomeMessage }.sendTo(event.player)
        println("joined @${System.currentTimeMillis()}")
    }

    @EventHandler
    fun playerLeave(event: PlayerQuitEvent) {
        service.viewerService.removeViewer(event.player)
    }
}