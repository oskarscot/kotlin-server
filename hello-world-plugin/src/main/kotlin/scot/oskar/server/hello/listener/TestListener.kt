package scot.oskar.server.hello.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import scot.oskar.server.hello.message.MessageService

class TestListener(private val service: MessageService): Listener {

    @EventHandler
    fun testEvent(event: PlayerJoinEvent) {
        service.getMessage { it.dupa }.sendTo(event.player)
    }
}