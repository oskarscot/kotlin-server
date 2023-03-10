package scot.oskar.server.api.database

import java.sql.Connection
import java.sql.DriverManager

class PostgresDatabase(private var databaseConfig: DatabaseConfiguration) {

    lateinit var connection: Connection

    fun connect() {
        try {
            Class.forName("org.postgresql.Driver")
            connection = DriverManager.getConnection(
                "jdbc:postgresql://${databaseConfig.host}:${databaseConfig.port}/${databaseConfig.database}",
                databaseConfig.username,
                databaseConfig.password
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        connection.close()
    }
}