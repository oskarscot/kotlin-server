package scot.oskar.server.hello

import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.hello.command.TestCommand
import scot.oskar.server.hello.listener.TestListener

@PluginInfo(
    name = "Hello World",
    version = "0.0.1",
    description = "A simple plugin that prints Hello World to the console",
    authors = ["Oskar"]
)
class HelloWorldPlugin(javaPlugin: JavaPlugin, commandHandler: BukkitCommandHandler) : KPlugin(javaPlugin, commandHandler) {

    override fun enable() {
        plugin.logger.info("Hello from KPlugin!")
        plugin.logger.info(plugin.server.bukkitVersion)

        registerCommands()
        registerEvents()
    }

    override fun disable() {
        println("Goodbye World!")
    }

    private fun registerCommands() {
        commandHandler.register(TestCommand())
    }

    private fun registerEvents() {
        plugin.server.pluginManager.registerEvents(TestListener(), plugin)
    }
}