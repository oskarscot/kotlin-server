package scot.oskar.server.api.database

import eu.okaeri.configs.OkaeriConfig

class DatabaseConfiguration: OkaeriConfig(){

    var databaseEnabled: Boolean = true
    var host: String = "localhost"
    var port: Int = 5432
    var database: String = "kotlin"
    var username: String = "postgres"
    var password: String = "dupa123"

}