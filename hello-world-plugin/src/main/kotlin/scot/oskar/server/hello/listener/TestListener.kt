package scot.oskar.server.hello.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import scot.oskar.server.hello.config.TestConfig

class TestListener(private val config: TestConfig): Listener {

    @EventHandler
    fun testEvent(event: PlayerJoinEvent) {
        event.player.sendMessage(config.test)
    }
}