package de.kuerbisskraft.manhunt

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class Manhunt : JavaPlugin(), Listener, CommandExecutor {
    private lateinit var cmdInterpreter: CmdInterpreter
    private lateinit var matchTick: MatchTick

    override fun onEnable() {
        matchTick = MatchTick(Bukkit.getLogger(), this)
        cmdInterpreter = CmdInterpreter(matchTick)

        Bukkit.getPluginManager().registerEvents(this, this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name == "manhunt") {
            return cmdInterpreter.onCommand(sender, args[0], args.slice(1 until args.count()))
        }

        return false
    }

    @EventHandler
    fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent) {
        if (event.advancement.key.key == "end/kill_dragon") {
            Bukkit.broadcastMessage("${ChatColor.RED}${matchTick.getSpeedrunnerName()} won the match!")

            matchTick.end(true)
        }
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        if (event.player.name != matchTick.getSpeedrunnerName()) {
            event.player.inventory.setItemInMainHand(ItemStack(Material.COMPASS, 1))
        }
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity

        if (Lib.getPlayerId(event.entity) == matchTick.getSpeedrunnerid()) {
            matchTick.end(false)
        } else {
            for (i in 0 until player.inventory.size) {
                val stack = player.inventory.getItem(i)
                if (stack != null && stack.type == Material.COMPASS) {
                    player.inventory.setItem(i, null)
                }
            }
            event.entity.teleportAsync(event.entity.location.world.spawnLocation)
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (matchTick.isSpeedrunner(event.player)) {
            matchTick.playSoundForHunters(
                Lib.absoluteToRelative(event.block.location, event.player.location),
                event.block.soundGroup.placeSound
            )
        }
    }

    @EventHandler
    fun onBlockDamage(event: BlockDamageEvent) {
        if (matchTick.isSpeedrunner(event.player)) {
            matchTick.playSoundForHunters(
                Lib.absoluteToRelative(event.block.location, event.player.location),
                event.block.soundGroup.hitSound
            )
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (matchTick.isSpeedrunner(event.player)) {
            matchTick.playSoundForHunters(
                Lib.absoluteToRelative(event.block.location, event.player.location),
                event.block.soundGroup.placeSound
            )
        }
    }

    @EventHandler
    fun onEntityHitEntity(event: EntityDamageByEntityEvent) {
        if (event.damager is Player && matchTick.isSpeedrunner(event.damager as Player)) {
            val hurtSound = EntitySounds.getHurtSound(event.entityType)
            if (hurtSound != null) {
                matchTick.playSoundForHunters(
                    Lib.absoluteToRelative(event.entity.location, event.damager.location),
                    hurtSound
                )
            }
        }
    }
}
