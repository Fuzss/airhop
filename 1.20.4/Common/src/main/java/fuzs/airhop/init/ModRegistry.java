package fuzs.airhop.init;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.world.item.enchantment.AirHopEnchantment;
import fuzs.puzzleslib.api.capability.v3.CapabilityController;
import fuzs.puzzleslib.api.capability.v3.data.EntityCapabilityKey;
import fuzs.puzzleslib.api.capability.v3.data.SyncStrategy;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.from(AirHop.MOD_ID);
    // having all armor slots here doesn't change anything in survival, but allows the enchantment to work when somehow present on other armor pieces besides leggings
    private static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public static final Holder.Reference<Enchantment> AIR_HOP_ENCHANTMENT = REGISTRY.registerEnchantment("air_hop", () -> new AirHopEnchantment(Enchantment.Rarity.RARE, ARMOR_SLOTS));
    public static final Holder.Reference<SoundEvent> ENTITY_PLAYER_HOP_SOUND = REGISTRY.registerSoundEvent("entity.player.hop");

    private static final CapabilityController CAPABILITIES = CapabilityController.from(AirHop.MOD_ID);
    public static final EntityCapabilityKey<Player, AirHopsCapability> AIR_HOPS_CAPABILITY = CAPABILITIES.registerEntityCapability("air_hops", AirHopsCapability.class, AirHopsCapability::new, Player.class).setSyncStrategy(SyncStrategy.PLAYER);

    public static void touch() {

    }
}
