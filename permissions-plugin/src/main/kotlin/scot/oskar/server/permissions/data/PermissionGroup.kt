package scot.oskar.server.permissions.data

data class PermissionGroup(val name: String, val permissions: Set<String>, val meta: Map<String, String>)
