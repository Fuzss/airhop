package fuzs.airhop.init;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.capability.AirHopsCapabilityImpl;
import fuzs.airhop.enchantment.AirHopEnchantment;
import fuzs.puzzleslib.capability.CapabilityController;
import fuzs.puzzleslib.capability.data.CapabilityKey;
import fuzs.puzzleslib.capability.data.PlayerRespawnStrategy;
import fuzs.puzzleslib.capability.data.SyncStrategy;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.init.RegistryManager;
import fuzs.puzzleslib.init.RegistryReference;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRegistry {
    private static final RegistryManager REGISTRY = CoreServices.FACTORIES.registration(AirHop.MOD_ID);
    // having all armor slots here doesn't change anything in survival, but allows the enchantment to work when somehow present on other armor pieces besides leggings
    public static final RegistryReference<Enchantment> AIR_HOP_ENCHANTMENT = REGISTRY.registerEnchantment("air_hop", () -> new AirHopEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET));
    public static final RegistryReference<SoundEvent> ENTITY_PLAYER_HOP_SOUND = REGISTRY.registerRawSoundEvent("entity.player.hop");

    private static final CapabilityController CAPABILITIES = CoreServices.FACTORIES.capabilities(AirHop.MOD_ID);
    public static final CapabilityKey<AirHopsCapability> AIR_HOPS_CAPABILITY = CAPABILITIES.registerPlayerCapability("air_hops", AirHopsCapability.class, player -> new AirHopsCapabilityImpl(), PlayerRespawnStrategy.NEVER, SyncStrategy.SELF);

    public static void touch() {

    }
}
