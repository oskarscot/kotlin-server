package scot.oskar.server.hello.message

import dev.peri.yetanothermessageslibrary.viewer.ViewerDataSupplier
import net.kyori.adventure.audience.Audience
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class SimpleViewerDataSupplier : ViewerDataSupplier<CommandSender, UUID> {
    override fun getAudience(receiver: CommandSender): Audience {
        return receiver
    }

    override fun isConsole(receiver: CommandSender): Boolean {
        return receiver != Bukkit.getConsoleSender()
    }

    override fun getKey(receiver: CommandSender): UUID {
        if(receiver == Bukkit.getConsoleSender()) {
            return UUID(0, 0)
        }
        return (receiver as Player).uniqueId
    }
}