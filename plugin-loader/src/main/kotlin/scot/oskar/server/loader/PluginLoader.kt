package scot.oskar.server.loader

import org.bukkit.Server
import org.bukkit.plugin.java.JavaPlugin
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile
import java.util.logging.Logger

class PluginLoader: JavaPlugin() {

    private val plugins = mutableListOf<KPlugin>()

    override fun onEnable() {
        val pluginFolder = File(server.worldContainer.absolutePath, "kotlin-plugins").apply { mkdirs() }
        val pluginFiles = pluginFolder.listFiles { file -> file.extension == "jar" } ?: return

        if(pluginFiles.isEmpty()) {
            logger.info("No plugins found")
            return
        }

        pluginFiles.forEach { pluginFile ->
            val jarFile = JarFile(pluginFile)
            val entries = jarFile.entries()

            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()

                if (!entry.name.endsWith(".class") || entry.name.contains("module-info")) {
                    continue
                }

                val className = entry.name.substring(0, entry.name.length - 6).replace('/', '.')
                val classLoader = URLClassLoader(arrayOf(pluginFile.toURI().toURL()), javaClass.classLoader)

                try {
                    val pluginClass = Class.forName(className, true, classLoader)

                    if (!KPlugin::class.java.isAssignableFrom(pluginClass)) {
                        continue
                    }

                    val pluginInfo = pluginClass.getAnnotation(PluginInfo::class.java) ?: continue

                    val pluginInstance = pluginClass.getConstructor(Server::class.java, Logger::class.java)
                        .newInstance(server, logger) as KPlugin

                    logger.info("Loading plugin ${pluginInfo.name} v${pluginInfo.version} by ${pluginInfo.authors.joinToString(", ")}")

                    try {
                        pluginInstance.enable()
                        plugins.add(pluginInstance)
                    } catch (e: Exception) {
                        logger.warning("Failed to enable plugin ${pluginInfo.name}")
                        e.printStackTrace()
                    }
                } catch (e: Exception) {
                    logger.warning("Failed to load class $className from plugin ${pluginFile.name}")
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onDisable() {
        plugins.forEach { plugin ->
            try {
                plugin.disable()
            } catch (e: Exception) {
                logger.warning("Failed to disable plugin ${plugin.javaClass.getAnnotation(PluginInfo::class.java)?.name}")
                e.printStackTrace()
            }
        }
    }
}