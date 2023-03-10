package scot.oskar.server.hello

import dev.peri.yetanothermessageslibrary.config.serdes.SerdesMessages
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.hello.command.TestCommand
import scot.oskar.server.hello.config.TestConfig
import scot.oskar.server.hello.listener.TestListener
import scot.oskar.server.hello.message.MessageConfig
import scot.oskar.server.hello.message.MessageService
import java.util.*
import kotlin.concurrent.timerTask

@PluginInfo(
    name = "hello-world-plugin",
    version = "0.0.1",
    description = "A simple plugin that prints Hello World to the console",
    authors = ["oskarscot"]
)
class HelloWorldPlugin(javaPlugin: JavaPlugin, commandHandler: BukkitCommandHandler) : KPlugin(javaPlugin, commandHandler) {

    private lateinit var config: TestConfig
    private lateinit var messageConfig: MessageConfig
    private val messageService = MessageService { task, delay ->
        plugin.server.scheduler.runTaskLater(
            plugin,
            task,
            delay
        )
    }

    override fun enable() {
        config = registerConfigDefault(TestConfig::class.java, "test")
        messageConfig = registerConfig(MessageConfig::class.java, config.locale.toLanguageTag(), SerdesMessages())
        messageService.defaultLocale = config.locale
        messageService.registerRepository(config.locale, messageConfig)

        plugin.logger.info(config.locale.toLanguageTag())

        registerCommands()
        registerEvents()
    }

    override fun disable() {
        plugin.logger.info("Goodbye from KPlugin!")
    }

    private fun registerCommands() {
        commandHandler.register(TestCommand())
    }

    private fun registerEvents() {
        plugin.server.pluginManager.registerEvents(TestListener(messageService), plugin)
    }
}