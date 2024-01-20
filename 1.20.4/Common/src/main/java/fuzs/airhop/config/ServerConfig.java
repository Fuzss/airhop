package fuzs.airhop.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;

public class ServerConfig implements ConfigCore {
    @Config(name = "spawn_particle_cloud", description = "Spawn a small particle cloud at the players feet on every air hop.")
    public boolean summonCloud = true;
    @Config(name = "play_hopping_sound", description = "Play a funny sound effect whenever the player hops in mid-air.")
    public boolean hopSound = true;
    @Config(name = "boots_damage_chance", description = "Chance the player's boots will be damaged by an air hop.")
    @Config.DoubleRange(min = 0.0, max = 1.0)
    public double damageChance = 0.15;
    @Config(name = "normal_fall_damage", description = "Take normal fall damage when hitting the ground after air hopping.")
    public boolean fallDamage = false;
    @Config(name = "only_when_falling", description = "Air hopping can only be used while falling to prevent gaining too much height.")
    public boolean fallingOnly = false;
    @Config(name = "disable_on_hungry", description = "Prevent air hop enchantment from working when the player has 6 or less food points.")
    public boolean disableOnHungry = true;
    @Config(category = "enchantment", description = "Maximum level for this enchantment. Each level provides one additional air hop.")
    @Config.IntRange(min = 1, max = 255)
    public int maxEnchantmentLevel = 3;
    @Config(category = "enchantment", description = "Makes the enchantment unobtainable from enchanting tables. It is only available on items from loot chests, fishing and villager trading.")
    public boolean treasureEnchantment = true;
}
