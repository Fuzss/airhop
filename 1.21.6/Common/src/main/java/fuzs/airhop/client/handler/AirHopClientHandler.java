package fuzs.airhop.client.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.init.ModRegistry;
import fuzs.airhop.network.client.ServerboundAirHopMessage;
import fuzs.puzzleslib.api.network.v4.MessageSender;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.apache.commons.lang3.mutable.MutableInt;

public class AirHopClientHandler {

    public static void onEndPlayerTick(Player player) {
        if (player.getAbilities().flying) {
            // don't use an air hop immediately after stopping creative mode flight
            player.noJumpDelay = 10;
        } else if (player.jumping && player.noJumpDelay == 0 && attemptJump(player)) {
            // prevent accidental usage of air hops
            player.noJumpDelay = 10;
            // trigger jump on server
            MessageSender.broadcast(new ServerboundAirHopMessage());
        }
    }

    private static boolean attemptJump(Player player) {
        if (canJump(player) && isSaturated(player)) {
            if (ModRegistry.AIR_HOPS_ATTACHMENT_TYPE.getOrDefault(player, (byte) 0) <
                    getHighestLevel(player, ModRegistry.AIR_HOP_ENCHANTMENT_EFFECT_COMPONENT_TYPE.value())) {
                player.jumpFromGround();
                player.resetFallDistance();
                ModRegistry.AIR_HOPS_ATTACHMENT_TYPE.update(player, airHops -> ++airHops);
                return true;
            }
        }
        return false;
    }

    private static boolean canJump(Player player) {
        if (!player.onGround()) {
            if (!AirHop.CONFIG.get(ServerConfig.class).fallingOnly ||
                    PlayerFallHandler.getJumpHeight(player) / 2.0F < player.fallDistance) {
                if (!(player.isPassenger() || player.getAbilities().flying || player.onClimbable())) {
                    return !(player.isInWater() || player.isInLava());
                }
            }
        }
        return false;
    }

    private static boolean isSaturated(Player player) {
        // air hopping always works in creative mode
        return player.getAbilities().mayfly || !AirHop.CONFIG.get(ServerConfig.class).disableOnHungry ||
                player.getFoodData().getFoodLevel() > 6;
    }

    /**
     * An adapted version of {@link EnchantmentHelper#getHighestLevel(ItemStack, DataComponentType)}, using
     * {@link EnchantmentHelper#runIterationOnEquipment(LivingEntity, EnchantmentHelper.EnchantmentInSlotVisitor)}
     * instead of {@link EnchantmentHelper#runIterationOnItem(ItemStack, EnchantmentHelper.EnchantmentVisitor)}.
     */
    private static <T> int getHighestLevel(LivingEntity entity, DataComponentType<T> componentType) {
        MutableInt mutableInt = new MutableInt();
        EnchantmentHelper.runIterationOnEquipment(entity,
                (Holder<Enchantment> holder, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse) -> {
                    if (mutableInt.getValue() < enchantmentLevel) {
                        T t = holder.value().effects().get(componentType);
                        if (t != null) {
                            mutableInt.setValue(enchantmentLevel);
                        }
                    }
                });
        return mutableInt.getValue();
    }
}
