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
        this.tag(EnchantmentTags.TREASURE).addKey(ModRegistry.AIR_HOP_ENCHANTMENT);
        this.tag(EnchantmentTags.ON_RANDOM_LOOT).addKey(ModRegistry.AIR_HOP_ENCHANTMENT);
        this.tag(EnchantmentTags.TRADEABLE).addKey(ModRegistry.AIR_HOP_ENCHANTMENT);
    }
}
