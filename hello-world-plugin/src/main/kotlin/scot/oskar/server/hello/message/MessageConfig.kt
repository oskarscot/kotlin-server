package scot.oskar.server.hello.message

import dev.peri.yetanothermessageslibrary.MessageRepository
import dev.peri.yetanothermessageslibrary.message.SendableMessage
import dev.peri.yetanothermessageslibrary.message.holder.impl.ChatHolder
import eu.okaeri.configs.OkaeriConfig

class MessageConfig: OkaeriConfig(), MessageRepository{

    var dupa: SendableMessage = SendableMessage.builder()
        .actionBar("action bar message")
        .chat("chat message")
        .title("title message", "subtitle message", 10, 80, 10)
        .build();
}