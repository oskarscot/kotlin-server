package scot.oskar.server.hello.message

import dev.peri.yetanothermessageslibrary.message.BukkitMessageDispatcher
import dev.peri.yetanothermessageslibrary.message.Sendable
import dev.peri.yetanothermessageslibrary.viewer.Viewer
import dev.peri.yetanothermessageslibrary.viewer.ViewerService
import org.bukkit.command.CommandSender
import java.util.*
import java.util.function.Function

class SimpleMessageDispatcher(
    viewerService: ViewerService<CommandSender, out Viewer>,
    localeSupplier: Function<Any?, Locale>,
    messageSupplier: Function<Any?, Sendable?>
) : BukkitMessageDispatcher<SimpleMessageDispatcher>(viewerService, localeSupplier, messageSupplier)