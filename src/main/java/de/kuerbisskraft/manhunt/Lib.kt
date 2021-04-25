package de.kuerbisskraft.manhunt

import org.bukkit.Location
import org.bukkit.entity.Player

object Lib {
    fun getPlayerId(player: Player): String {
        return player.name
    }

    fun absoluteToRelative(location: Location, relation: Location): Location {
        return Location(
            location.world,
            location.x - relation.x,
            location.y - relation.y,
            location.z - relation.z
        )
    }

    fun relativeToAbsolute(relative: Location, target: Location): Location {
        return Location(
            target.world,
            relative.x + target.x,
            relative.y + target.y,
            relative.z + target.z
        )
    }
}
