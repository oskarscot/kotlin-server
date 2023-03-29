package scot.oskar.server.permissions.data

import java.util.UUID

data class PermissionPlayer(val uuid: UUID, var name: String, var primaryGroup: String, var permissions: Set<String>) {

}
