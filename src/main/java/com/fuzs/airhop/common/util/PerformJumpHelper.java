package com.fuzs.airhop.common.util;

import com.fuzs.airhop.capability.AirHopCapabilities;
import com.fuzs.airhop.capability.storage.AirHopsCapability;
import com.fuzs.airhop.config.ConfigBuildHandler;
import com.fuzs.airhop.enchantment.AirHopEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PerformJumpHelper {

    @SuppressWarnings("ConstantConditions")
    public boolean doJump(PlayerEntity player, boolean sneaking) {

        if (!this.allowJump(player, sneaking)) {

            return false;
        }

        int jumps = player.getCapability(AirHopCapabilities.AIR_HOPS).map(AirHopsCapability::getAirHops).orElse(Integer.MAX_VALUE);
        if (jumps < EnchantmentHelper.getMaxEnchantmentLevel(AirHopEnchantments.AIR_HOP, player)) {

            player.jump();
            // it's reset on the server anyways for every jump, so might as well do it here for both sides
            player.fallDistance = 0.0F;
            player.getCapability(AirHopCapabilities.AIR_HOPS).ifPresent(AirHopsCapability::addAirHop);

            this.addExtraExhaustion(player);
            if (player.getRNG().nextDouble() < ConfigBuildHandler.DAMAGE_CHANCE.get()) {

                ItemStack itemstack = player.getItemStackFromSlot(EquipmentSlotType.FEET);
                itemstack.damageItem(1, player, player1 -> player1.sendBreakAnimation(EquipmentSlotType.FEET));
            }

            return true;
        }

        return false;
    }

    private boolean allowJump(PlayerEntity player, boolean sneaking) {

        boolean performingAction = player.isOnGround() || player.isPassenger() || player.abilities.isFlying;
        boolean insideLiquid = player.isInWater() || player.isInLava();
        if (performingAction || insideLiquid) {

            return false;
        }

        ItemStack itemstack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        boolean fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemstack);
        if (ConfigBuildHandler.INVERT_ELYTRA.get()) {

            sneaking = !sneaking;
        }

        if (fallFlyingReady && !sneaking) {

            return false;
        }

        return player.abilities.allowFlying || !ConfigBuildHandler.DISABLE_ON_HUNGRY.get() || player.getFoodStats()
                .getFoodLevel() > ConfigBuildHandler.FOOD_THRESHOLD.get();
    }

    private void addExtraExhaustion(PlayerEntity player) {

        float f = (float) Math.max(ConfigBuildHandler.HOP_EXHAUSTION.get() - 1.0, 0);
        player.addExhaustion(player.isSprinting() ? 0.2F * f : 0.05F * f);
    }

}
