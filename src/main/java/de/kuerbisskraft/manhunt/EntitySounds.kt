package de.kuerbisskraft.manhunt

import org.bukkit.Sound
import org.bukkit.entity.EntityType

object EntitySounds {
    fun getHurtSound(entity: EntityType): Sound? {
        return when(entity) {
            EntityType.BAT -> Sound.ENTITY_BAT_HURT
            EntityType.BEE -> Sound.ENTITY_BEE_HURT
            EntityType.BLAZE -> Sound.ENTITY_BLAZE_HURT
            EntityType.CAT -> Sound.ENTITY_CAT_HURT
            EntityType.CAVE_SPIDER, EntityType.SPIDER -> Sound.ENTITY_SPIDER_HURT
            EntityType.CHICKEN -> Sound.ENTITY_CHICKEN_HURT
            EntityType.COW, EntityType.MUSHROOM_COW -> Sound.ENTITY_COW_HURT
            EntityType.CREEPER -> Sound.ENTITY_CREEPER_HURT
            EntityType.DOLPHIN -> Sound.ENTITY_DOLPHIN_HURT
            EntityType.DONKEY -> Sound.ENTITY_DONKEY_HURT
            EntityType.ELDER_GUARDIAN -> Sound.ENTITY_ELDER_GUARDIAN_HURT
            EntityType.ENDERMAN -> Sound.ENTITY_ENDERMAN_HURT
            EntityType.ENDERMITE -> Sound.ENTITY_ENDERMITE_HURT
            EntityType.ENDER_DRAGON -> Sound.ENTITY_ENDER_DRAGON_HURT
            EntityType.EVOKER -> Sound.ENTITY_EVOKER_HURT
            EntityType.FOX -> Sound.ENTITY_FOX_HURT
            EntityType.GHAST -> Sound.ENTITY_GHAST_HURT
            EntityType.GUARDIAN -> Sound.ENTITY_GUARDIAN_HURT
            EntityType.HOGLIN -> Sound.ENTITY_HOGLIN_HURT
            EntityType.HUSK -> Sound.ENTITY_HUSK_HURT
            EntityType.HORSE -> Sound.ENTITY_HORSE_HURT
            EntityType.ILLUSIONER -> Sound.ENTITY_ILLUSIONER_HURT
            EntityType.LLAMA, EntityType.TRADER_LLAMA -> Sound.ENTITY_LLAMA_HURT
            EntityType.IRON_GOLEM -> Sound.ENTITY_IRON_GOLEM_HURT
            EntityType.MULE -> Sound.ENTITY_MULE_HURT
            EntityType.OCELOT -> Sound.ENTITY_OCELOT_HURT
            EntityType.PANDA -> Sound.ENTITY_PANDA_HURT
            EntityType.PARROT -> Sound.ENTITY_PARROT_HURT
            EntityType.PHANTOM -> Sound.ENTITY_PHANTOM_HURT
            EntityType.PIG -> Sound.ENTITY_PIG_HURT
            EntityType.PIGLIN -> Sound.ENTITY_PIGLIN_HURT
            EntityType.PIGLIN_BRUTE -> Sound.ENTITY_PIGLIN_BRUTE_HURT
            EntityType.PILLAGER -> Sound.ENTITY_PILLAGER_HURT
            EntityType.PLAYER -> Sound.ENTITY_PLAYER_HURT
            EntityType.POLAR_BEAR -> Sound.ENTITY_POLAR_BEAR_HURT
            EntityType.PUFFERFISH -> Sound.ENTITY_PUFFER_FISH_HURT
            EntityType.RABBIT -> Sound.ENTITY_RABBIT_HURT
            EntityType.RAVAGER -> Sound.ENTITY_RAVAGER_HURT
            EntityType.SHEEP -> Sound.ENTITY_SHEEP_HURT
            EntityType.SHULKER -> Sound.ENTITY_SHULKER_HURT
            EntityType.SILVERFISH -> Sound.ENTITY_SILVERFISH_HURT
            EntityType.SALMON -> Sound.ENTITY_SALMON_HURT
            EntityType.SKELETON -> Sound.ENTITY_SKELETON_HURT
            EntityType.SKELETON_HORSE -> Sound.ENTITY_SKELETON_HORSE_HURT
            EntityType.SLIME -> Sound.ENTITY_SLIME_HURT
            EntityType.SNOWMAN -> Sound.ENTITY_SNOW_GOLEM_HURT
            EntityType.SQUID -> Sound.ENTITY_SQUID_HURT
            EntityType.STRAY -> Sound.ENTITY_STRAY_HURT
            EntityType.STRIDER -> Sound.ENTITY_STRIDER_HURT
            EntityType.TURTLE -> Sound.ENTITY_TURTLE_HURT
            EntityType.VEX -> Sound.ENTITY_VEX_HURT
            EntityType.VINDICATOR -> Sound.ENTITY_VINDICATOR_HURT
            EntityType.VILLAGER -> Sound.ENTITY_VILLAGER_HURT
            EntityType.WANDERING_TRADER -> Sound.ENTITY_WANDERING_TRADER_HURT
            EntityType.WITCH -> Sound.ENTITY_WITCH_HURT
            EntityType.WITHER -> Sound.ENTITY_WITHER_HURT
            EntityType.WITHER_SKELETON -> Sound.ENTITY_WITHER_SKELETON_HURT
            EntityType.WOLF -> Sound.ENTITY_WOLF_HURT
            EntityType.ZOGLIN -> Sound.ENTITY_ZOGLIN_HURT
            EntityType.ZOMBIE -> Sound.ENTITY_ZOMBIE_HURT
            EntityType.ZOMBIE_HORSE -> Sound.ENTITY_ZOMBIE_HORSE_HURT
            EntityType.ZOMBIE_VILLAGER -> Sound.ENTITY_ZOMBIE_VILLAGER_HURT
            EntityType.ZOMBIFIED_PIGLIN -> Sound.ENTITY_ZOMBIFIED_PIGLIN_HURT
            else -> null
        }
    }
}
