package fuzs.airhop.data;

import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentTagProvider extends AbstractTagProvider<Enchantment> {

    public ModEnchantmentTagProvider(DataProviderContext context) {
        super(Registries.ENCHANTMENT, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.add(EnchantmentTags.TREASURE).add(ModRegistry.AIR_HOP_ENCHANTMENT);
        this.add(EnchantmentTags.ON_RANDOM_LOOT).add(ModRegistry.AIR_HOP_ENCHANTMENT);
        this.add(EnchantmentTags.TRADEABLE).add(ModRegistry.AIR_HOP_ENCHANTMENT);
    }
}
