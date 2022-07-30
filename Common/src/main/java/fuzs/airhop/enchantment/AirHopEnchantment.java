package fuzs.airhop.enchantment;

import fuzs.airhop.AirHop;
import fuzs.airhop.config.ServerConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.item.enchantment.WaterWalkerEnchantment;

/**
 * values based on frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {

    public AirHopEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_LEGS, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {
        // used to be a problem with creative inventory enchanted books, but seems to work fine now (book max levels are reflected in the inventory, not even a game reload required)
        return AirHop.CONFIG.get(ServerConfig.class).maxEnchantmentLevel;
    }

    @Override
    public boolean isTreasureOnly() {
        return AirHop.CONFIG.get(ServerConfig.class).treasureEnchantment;
    }
}
