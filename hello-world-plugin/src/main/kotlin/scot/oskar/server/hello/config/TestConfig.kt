package scot.oskar.server.hello.config

import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.NameModifier
import eu.okaeri.configs.annotation.NameStrategy
import eu.okaeri.configs.annotation.Names
import java.util.Locale


@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class TestConfig: OkaeriConfig() {

    var test: String = "Hello World!"
    var locale: Locale = Locale.forLanguageTag("pl")

}