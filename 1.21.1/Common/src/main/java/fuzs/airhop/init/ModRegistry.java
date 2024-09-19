package fuzs.airhop.init;

import com.mojang.serialization.Codec;
import fuzs.airhop.AirHop;
import fuzs.puzzleslib.api.attachment.v4.DataAttachmentRegistry;
import fuzs.puzzleslib.api.attachment.v4.DataAttachmentType;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(AirHop.MOD_ID);
    public static final ResourceKey<Enchantment> AIR_HOP_ENCHANTMENT = REGISTRIES.registerEnchantment("air_hop");
    public static final Holder.Reference<SoundEvent> ENTITY_PLAYER_HOP_SOUND_EVENT = REGISTRIES.registerSoundEvent(
            "entity.player.hop");
    public static final Holder.Reference<DataComponentType<Unit>> AIR_HOP_ENCHANTMENT_EFFECT_COMPONENT_TYPE = REGISTRIES.register(
            Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, "air_hop",
            () -> DataComponentType.<Unit>builder().persistent(Unit.CODEC).build()
    );

    public static final DataAttachmentType<Entity, Byte> AIR_HOPS_ATTACHMENT_TYPE = DataAttachmentRegistry.<Byte>entityBuilder()
            .defaultValue(EntityType.PLAYER, (byte) 0)
            .persistent(Codec.BYTE)
            .networkSynchronized(ByteBufCodecs.BYTE)
            .build(AirHop.id("air_hops"));

    public static void touch() {
        // NO-OP
    }
}
