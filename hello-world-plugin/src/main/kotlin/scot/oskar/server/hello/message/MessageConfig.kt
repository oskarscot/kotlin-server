package scot.oskar.server.hello.message

import dev.peri.yetanothermessageslibrary.MessageRepository
import dev.peri.yetanothermessageslibrary.adventure.MiniComponent
import dev.peri.yetanothermessageslibrary.message.SendableMessage
import eu.okaeri.configs.OkaeriConfig

class MessageConfig: OkaeriConfig(), MessageRepository{

    var welcomeMessage: SendableMessage = SendableMessage.builder()
        .actionBar(MiniComponent.of("<red>action bar message"))
        .chat(MiniComponent.of("<green>chat message"))
        .title(MiniComponent.of("<rainbow>Title"), MiniComponent.of("<rainbow>Subtitle"), 10, 80, 10)
        .build()
}