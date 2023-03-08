package scot.oskar.server.api

annotation class PluginInfo(
    val name: String,
    val version: String,
    val description: String = "No Description Provided",
    val authors: Array<String> = [],
    val dependencies: Array<String> = [],
)
