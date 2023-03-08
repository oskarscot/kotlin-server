package scot.oskar.server.api

import org.bukkit.Server
import java.util.logging.Logger

abstract class KPlugin(val server: Server, val logger: Logger) {
    abstract fun enable();

    abstract fun disable();

}