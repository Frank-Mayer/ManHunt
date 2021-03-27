package de.kuerbisskraft.manhunt

import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.CompassMeta
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.concurrent.timerTask

class MatchTick(private val logger: Logger, private val plugin: Plugin) {
    private var speedrunnerId: String? = null
    private var speedrunnerPlayer: Player? = null
    private var time = 0

    init {
        val tick = Timer()
        tick.schedule(timerTask {
            onTick()
        }, 1000, 1000)
    }

    fun start(speedrunner: Player) {
        speedrunnerId = Lib.getPlayerId(speedrunner)
        speedrunnerPlayer = speedrunner
        time = 0
        setupPlayers()
    }

    fun start(speedrunner: String) {
        speedrunnerPlayer = Bukkit.getPlayer(speedrunner)
        if (speedrunnerPlayer != null) {
            speedrunnerId = speedrunner
            time = 0
            setupPlayers()
        }
    }

    private fun setupPlayers() {
        for (world in Bukkit.getWorlds()) {
            world.difficulty = Difficulty.EASY
        }

        for (player in Bukkit.getOnlinePlayers()) {
            player.gameMode = GameMode.SURVIVAL
            player.inventory.clear()
            for (effect in player.activePotionEffects) {
                player.removePotionEffect(effect.type)
            }
            player.allowFlight = false
            player.level = 0
            player.exp = 0.0f
            player.health = 20.0
            player.foodLevel = 20

            if (Lib.getPlayerId(player) != speedrunnerId) {
                val compass = ItemStack(Material.COMPASS, 1)
                val meta = compass.itemMeta as CompassMeta
                meta.setDisplayName("${ChatColor.GREEN}Radar")
                compass.itemMeta = meta
                player.inventory.setItemInMainHand(compass)

                player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 300, 1, false, false, false))
                player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 300, 2, false, false, false))
                player.addPotionEffect(PotionEffect(PotionEffectType.HEAL, 300, 2, false, false, false))
                player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 10, false, false, false))
            }

            val iterator = Bukkit.getServer().advancementIterator()
            while (iterator.hasNext()) {
                val progress = player.getAdvancementProgress(iterator.next())
                for (criteria in progress.awardedCriteria) {
                    progress.revokeCriteria(criteria!!)
                }
            }
        }
    }

    fun timeFormat(): String {
        return "${time / 60}:${time % 60}"
    }

    fun end(speedrunnerWon: Boolean) {
        Bukkit.broadcastMessage("${ChatColor.RED}Time: ${timeFormat()}")

        for (world in Bukkit.getWorlds()) {
            world.difficulty = Difficulty.PEACEFUL
        }

        if (speedrunnerWon && speedrunnerPlayer != null) {
            speedrunnerPlayer!!.addPotionEffect(
                PotionEffect(
                    PotionEffectType.DAMAGE_RESISTANCE,
                    400,
                    10,
                    true,
                    false,
                    false
                )
            )
            speedrunnerPlayer!!.addPotionEffect(PotionEffect(PotionEffectType.HEAL, 400, 10, true, false, false))
            speedrunnerPlayer!!.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 400, 1, true, false, false))

            Bukkit.broadcastMessage("${ChatColor.GOLD}${getSpeedrunnerName()} won!")

            speedrunnerPlayer!!.playSound(speedrunnerPlayer!!.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f)

            for (player in Bukkit.getOnlinePlayers()) {
                if (Lib.getPlayerId(player) == speedrunnerId) {
                    player.playSound(player.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.5f, 1.0f)
                } else {
                    player.playSound(player.location, Sound.EVENT_RAID_HORN, 1.5f, 1.0f)
                }
            }
        } else {
            Bukkit.broadcastMessage("${ChatColor.RED}${getSpeedrunnerName()} died!")

            for (player in Bukkit.getOnlinePlayers()) {
                if (Lib.getPlayerId(player) == speedrunnerId) {
                    player.playSound(player.location, Sound.EVENT_RAID_HORN, 1.5f, 1.0f)
                } else {
                    player.playSound(player.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.5f, 1.0f)
                }
            }
        }

        speedrunnerId = null
        speedrunnerPlayer = null
    }

    fun getSpeedrunnerName(): String {
        return if (speedrunnerPlayer != null) {
            speedrunnerPlayer!!.name
        } else {
            return "NULL"
        }
    }

    fun getSpeedrunnerid(): String {
        return if (speedrunnerId != null) {
            speedrunnerId!!
        } else {
            return "NULL"
        }
    }

    private fun onTick() {
        if (speedrunnerId != null && speedrunnerPlayer != null) {
            time++

            if (time % 3 == 0) {
                for (player in Bukkit.getOnlinePlayers()) {
                    if (player.name == getSpeedrunnerName() || player.location.world != speedrunnerPlayer!!.location.world) {
                        continue
                    }

                    for (i in 0 until player.inventory.size) {
                        val stack = player.inventory.getItem(i)
                        if (stack != null && stack.type == Material.COMPASS) {
                            val meta = (player.inventory.getItem(i)?.itemMeta as CompassMeta)
                            meta.isLodestoneTracked = false
                            meta.lodestone = speedrunnerPlayer!!.location
                            player.inventory.getItem(i)?.itemMeta = meta
                            logger.log(Level.INFO, "Compass now pointing to ${speedrunnerPlayer!!.name}")
                        }
                    }
                }
            }
        } else {
            time = 0
        }
    }
}
