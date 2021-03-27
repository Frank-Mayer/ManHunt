package de.kuerbisskraft.manhunt

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdInterpreter(private val matchTick: MatchTick) {
    fun onCommand(sender: CommandSender, command: String, args: List<String>): Boolean {
        if (!sender.isOp) {
            return false
        }

        val player: Player? = if (args.count() >= 1) {
            if (args[0] == "@r") {
                Bukkit.getOnlinePlayers().random()
            } else {
                Bukkit.getPlayer(args[0])
            }
        } else {
            null
        }

        when (command) {
            "start" -> {
                if (player != null) {
                    matchTick.start(player)
                    Bukkit.broadcastMessage("${ChatColor.RED}ManHunt started\nSpeedrunner: ${player.name}")
                    return true
                }
            }

            "time" -> {
                Bukkit.broadcastMessage("${ChatColor.AQUA}Time: ${matchTick.timeFormat()}")
            }
        }
        return false
    }
}
