package scot.oskar.server.api

import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.serdes.OkaeriSerdesPack
import eu.okaeri.configs.serdes.commons.SerdesCommons
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import java.io.File
import java.nio.file.Path

abstract class KPlugin(val plugin: JavaPlugin, val commandHandler: BukkitCommandHandler) {

    var fileLocation: Path? = null

    abstract fun enable();

    abstract fun disable();

   fun <T: OkaeriConfig> registerConfigDefault(config: Class<T>, fileName: String): T {
        return registerConfig(config, fileName, SerdesBukkit(), SerdesCommons())
   }

    fun <T : OkaeriConfig> registerConfig(config: Class<T>, fileName: String, vararg serdesPack: OkaeriSerdesPack): T {
        return ConfigManager.create(config) {
            it.withConfigurer(YamlBukkitConfigurer(), *serdesPack)
                .withBindFile(File(fileLocation?.toFile(), "$fileName.yml"))
                .saveDefaults()
                .load(true)
        }
    }

}