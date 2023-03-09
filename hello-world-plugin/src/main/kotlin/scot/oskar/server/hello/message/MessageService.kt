package scot.oskar.server.hello.message

import dev.peri.yetanothermessageslibrary.SimpleSendableMessageService
import dev.peri.yetanothermessageslibrary.message.MessageDispatcherFactory
import dev.peri.yetanothermessageslibrary.message.Sendable
import dev.peri.yetanothermessageslibrary.viewer.*
import net.kyori.adventure.audience.Audience
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Function


class MessageService(schedule: (Runnable, Long) -> Unit) :
    SimpleSendableMessageService<CommandSender, MessageConfig, SimpleMessageDispatcher>(
        SimpleViewerService(
            SimpleViewerDataSupplier()
        ) { _: CommandSender, audience: Audience, console: Boolean ->
            SimpleViewer(
                audience, console, schedule
            )
        },
        MessageDispatcherFactory { viewerService: ViewerService<CommandSender, out Viewer>, localeSupplier: Function<Any?, Locale>, messageSupplier: Function<Any?, Sendable?> ->
            SimpleMessageDispatcher(viewerService, localeSupplier, messageSupplier)
        }
    )