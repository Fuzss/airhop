package fuzs.airhop.init;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.capability.AirHopsCapabilityImpl;
import fuzs.airhop.world.item.enchantment.AirHopEnchantment;
import fuzs.puzzleslib.api.capability.v2.CapabilityController;
import fuzs.puzzleslib.api.capability.v2.data.CapabilityKey;
import fuzs.puzzleslib.api.capability.v2.data.PlayerRespawnCopyStrategy;
import fuzs.puzzleslib.api.capability.v2.data.SyncStrategy;
import fuzs.puzzleslib.api.init.v2.RegistryManager;
import fuzs.puzzleslib.api.init.v2.RegistryReference;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.instant(AirHop.MOD_ID);
    // having all armor slots here doesn't change anything in survival, but allows the enchantment to work when somehow present on other armor pieces besides leggings
    private static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public static final RegistryReference<Enchantment> AIR_HOP_ENCHANTMENT = REGISTRY.registerEnchantment("air_hop", () -> new AirHopEnchantment(Enchantment.Rarity.RARE, ARMOR_SLOTS));
    public static final RegistryReference<SoundEvent> ENTITY_PLAYER_HOP_SOUND = REGISTRY.registerSoundEvent("entity.player.hop");

    private static final CapabilityController CAPABILITIES = CapabilityController.from(AirHop.MOD_ID);
    public static final CapabilityKey<AirHopsCapability> AIR_HOPS_CAPABILITY = CAPABILITIES.registerPlayerCapability("air_hops", AirHopsCapability.class, player -> new AirHopsCapabilityImpl(), PlayerRespawnCopyStrategy.NEVER, SyncStrategy.SELF);

    public static void touch() {

    }
}
