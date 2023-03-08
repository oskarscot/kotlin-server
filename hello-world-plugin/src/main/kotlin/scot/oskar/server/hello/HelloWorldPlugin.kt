package scot.oskar.server.hello

import org.bukkit.Server
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo
import java.util.logging.Logger

@PluginInfo(
    name = "Hello World",
    version = "0.0.1",
    description = "A simple plugin that prints Hello World to the console",
    authors = ["Oskar"]
)
class HelloWorldPlugin(server: Server, logger: Logger) : KPlugin(server, logger) {

    override fun enable() {
        logger.info("Hello from KPlugin!")
        logger.info(server.bukkitVersion)
    }

    override fun disable() {
        println("Goodbye World!")
    }
}