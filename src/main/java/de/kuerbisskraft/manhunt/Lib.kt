package de.kuerbisskraft.manhunt

import org.bukkit.entity.Player

object Lib {
    fun getPlayerId(player: Player): String {
        return player.name
    }
}
