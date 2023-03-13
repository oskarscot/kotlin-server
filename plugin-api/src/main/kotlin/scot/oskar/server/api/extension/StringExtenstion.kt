package scot.oskar.server.api.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

val miniMessage = MiniMessage.miniMessage()

fun String.toMiniMessage(): Component {
    return miniMessage.deserialize(this)
}