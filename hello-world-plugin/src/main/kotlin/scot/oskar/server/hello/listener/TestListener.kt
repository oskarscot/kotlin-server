package scot.oskar.server.hello.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class TestListener: Listener {

    @EventHandler
    fun testEvent(event: PlayerJoinEvent) {
        event.player.sendMessage("Hello ${event.player.name}!")
    }
}