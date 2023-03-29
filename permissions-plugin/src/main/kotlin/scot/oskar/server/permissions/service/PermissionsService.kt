package scot.oskar.server.permissions.service

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import scot.oskar.server.permissions.data.PermissionPlayer
import java.sql.Connection
import java.util.*
import java.util.concurrent.CompletableFuture

class PermissionsService(private val connection: Connection) {

    val cache: Cache<UUID, PermissionPlayer> = CacheBuilder.newBuilder().build()

    fun getPermissionPlayer(uuid: UUID): CompletableFuture<PermissionPlayer?> {
        val cachedPlayer = cache.getIfPresent(uuid)
        return if (cachedPlayer != null) {
            CompletableFuture.completedFuture(cachedPlayer)
        } else {
            CompletableFuture.supplyAsync {
                val statement = connection.prepareStatement("SELECT * FROM permission_players WHERE uuid = ?")
                statement.setString(1, uuid.toString())
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    val name = resultSet.getString("name")
                    val primaryGroup = resultSet.getString("primary_group")
                    val permissions = resultSet.getString("permissions").split(",").toSet()
                    val player = PermissionPlayer(uuid, name, primaryGroup, permissions)
                    cache.put(uuid, player)
                    return@supplyAsync player
                }
                return@supplyAsync null
            }
        }
    }

    fun createPermissionPlayer(player: PermissionPlayer) {
        val statement = connection.prepareStatement("INSERT INTO permission_players (uuid, name, primary_group, permissions) VALUES (?, ?, ?, ?)")
        statement.setString(1, player.uuid.toString())
        statement.setString(2, player.name)
        statement.setString(3, player.primaryGroup)
        statement.setString(4, player.permissions.joinToString(","))
        statement.executeUpdate()
        cache.put(player.uuid, player)
    }

    fun updatePermissionPlayer(player: PermissionPlayer) {
        val statement = connection.prepareStatement("UPDATE permission_players SET name = ?, primary_group = ?, permissions = ? WHERE uuid = ?")
        statement.setString(1, player.name)
        statement.setString(2, player.primaryGroup)
        statement.setString(3, player.permissions.joinToString(","))
        statement.setString(4, player.uuid.toString())
        statement.executeUpdate()
        cache.put(player.uuid, player)
    }
}