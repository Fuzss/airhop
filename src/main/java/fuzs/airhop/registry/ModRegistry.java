package fuzs.airhop.registry;

import fuzs.airhop.AirHop;
import fuzs.airhop.enchantment.AirHopEnchantment;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.of(AirHop.MOD_ID);
    public static final RegistryObject<Enchantment> AIR_HOP_ENCHANTMENT = REGISTRY.registerEnchantment("air_hop", () -> new AirHopEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));
    public static final RegistryObject<SoundEvent> ENTITY_PLAYER_HOP_SOUND = REGISTRY.registerRawSoundEvent("entity.player.hop");

    public static void touch() {

    }
}
