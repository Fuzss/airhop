package fuzs.airhop.init;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.enchantment.AirHopEnchantment;
import fuzs.puzzleslib.capability.CapabilityController;
import fuzs.puzzleslib.capability.data.CapabilityKey;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.init.RegistryManager;
import fuzs.puzzleslib.init.RegistryReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRegistry {
    private static final RegistryManager REGISTRY = CoreServices.FACTORIES.registration(AirHop.MOD_ID);
    public static final RegistryReference<Enchantment> AIR_HOP_ENCHANTMENT = REGISTRY.registerEnchantment("air_hop", () -> new AirHopEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.LEGS));
    public static final RegistryReference<SoundEvent> ENTITY_PLAYER_HOP_SOUND = REGISTRY.registerRawSoundEvent("entity.player.hop");

    public static final CapabilityKey<AirHopsCapability> AIR_HOPS_CAPABILITY = CapabilityController.makeCapabilityKey(new ResourceLocation(AirHop.MOD_ID, "air_hops"), AirHopsCapability.class);

    public static void touch() {

    }
}
