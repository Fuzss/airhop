package com.fuzs.airhop.helper;

import com.fuzs.airhop.capability.AirHopsCapability;
import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.enchantment.AirHopEnchantment;
import com.fuzs.airhop.handler.ConfigHandler;
import com.fuzs.airhop.handler.RegistryHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class JumpHelper {

    public static boolean doJump(PlayerEntity player, boolean sneaking) {

        if (!allowJump(player, sneaking)) {
            return false;
        }

        int jumps = player.getCapability(CapabilityHolder.airHopsCap).map(AirHopsCapability::getAirHops).orElse(Integer.MAX_VALUE);

        if (jumps < possibleJumps(player)) {

            player.jump();
            player.getCapability(CapabilityHolder.airHopsCap).ifPresent(AirHopsCapability::addAirHop);
            setFallDistance(player);
            extraExhaustion(player);

            return true;

        }

        return false;

    }

    private static boolean allowJump(PlayerEntity player, boolean sneaking) {

        boolean performingAction = player.onGround || player.isPassenger() || player.abilities.isFlying;
        boolean insideLiquid = player.isInWater() || player.isInLava();

        if (performingAction || insideLiquid) {
            return false;
        }

        ItemStack itemstack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        boolean fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemstack);

        if (ConfigHandler.GENERAL_CONFIG.invertElytra.get()) {
            sneaking = !sneaking;
        }

        if (fallFlyingReady && !sneaking) {
            return false;
        }

        return player.abilities.allowFlying || !ConfigHandler.GENERAL_CONFIG.disableOnHungry.get() || player.getFoodStats()
                .getFoodLevel() > ConfigHandler.GENERAL_CONFIG.foodThreshold.get();

    }

    private static int possibleJumps(PlayerEntity player) {

        return player.inventory.armorInventory.stream().mapToInt(itemStack -> Math.min(RegistryHandler.AIR_HOP.getMaxLevel(),
                EnchantmentHelper.getEnchantmentLevel(RegistryHandler.AIR_HOP, itemStack))).sum();

    }

    private static void setFallDistance(PlayerEntity player) {

        float f = -1.25F;
        player.fallDistance = ConfigHandler.GENERAL_CONFIG.resetFallDistance.get() ? f * player.getCapability(
                CapabilityHolder.airHopsCap).map(AirHopsCapability::getAirHops).orElse(
                        Integer.MAX_VALUE) : player.fallDistance + f;

    }

    private static void extraExhaustion(PlayerEntity player) {

        float f = (float) Math.max(ConfigHandler.GENERAL_CONFIG.hopExhaustion.get() - 1.0, 0);

        if (player.isSprinting()) {
            player.addExhaustion(0.2F * f);
        } else {
            player.addExhaustion(0.05F * f);
        }

    }

}
