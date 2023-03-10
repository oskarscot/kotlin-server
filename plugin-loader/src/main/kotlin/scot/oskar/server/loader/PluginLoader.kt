package scot.oskar.server.loader

import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import scot.oskar.server.api.KPlugin
import scot.oskar.server.api.PluginInfo
import scot.oskar.server.loader.command.KPluginsCommand
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile
class PluginLoader: JavaPlugin() {

    val plugins = mutableListOf<KPlugin>()

    lateinit var commandHandler: BukkitCommandHandler

    override fun onEnable() {
        commandHandler = BukkitCommandHandler.create(this)

        loadPlugins()

        commandHandler.registerBrigadier()
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

    fun loadPlugins() {
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

                val pluginClass: Class<*>

                try {
                    pluginClass = classLoader.loadClass(className)
                } catch (e: Exception) {
                    logger.warning("Failed to load class $className from plugin ${pluginFile.name}. Does the class throw an exception during initialization?")
                    continue
                }

                // Check if the class is a plugin, and if so, get the plugin info and create an instance
                if (!KPlugin::class.java.isAssignableFrom(pluginClass)) {
                    continue
                }

                val pluginInfo = pluginClass.getAnnotation(PluginInfo::class.java) ?: continue

                // Create an instance of the plugin and pass the command handler to it
                val pluginInstance = pluginClass.getConstructor(JavaPlugin::class.java, BukkitCommandHandler::class.java)
                    .newInstance(this, commandHandler) as KPlugin

                logger.info("Loading plugin ${pluginInfo.name} v${pluginInfo.version} by ${pluginInfo.authors.joinToString(", ")}")

                try {
                    val pluginDirectory = File(pluginFolder, pluginInfo.name).apply { mkdirs() }
                    pluginInstance.fileLocation = pluginDirectory.toPath()
                    pluginInstance.enable()
                    plugins.add(pluginInstance)
                } catch (e: Exception) {
                    logger.warning("Failed to enable plugin ${pluginInfo.name}")
                    e.printStackTrace()
                }
            }
            jarFile.close()
        }
        commandHandler.register(KPluginsCommand(this))
    }
}