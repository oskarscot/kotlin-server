package scot.oskar.server.hello.command

import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command

class TestCommand {

    @Command("test")
    fun test(sender: Player) {
        sender.sendMessage("Hello ${sender.name}!")
    }
}