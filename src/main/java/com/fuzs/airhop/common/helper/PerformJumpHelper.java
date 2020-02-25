package com.fuzs.airhop.common.helper;

import com.fuzs.airhop.capability.AirHopsCapability;
import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.config.ConfigBuildHandler;
import com.fuzs.airhop.enchantment.AirHopEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PerformJumpHelper {

    public boolean doJump(PlayerEntity player, boolean sneaking) {

        if (!allowJump(player, sneaking)) {
            return false;
        }

        int jumps = player.getCapability(CapabilityHolder.airHopsCap).map(AirHopsCapability::getAirHops).orElse(Integer.MAX_VALUE);

        if (jumps < this.possibleJumps(player)) {

            player.jump();
            player.getCapability(CapabilityHolder.airHopsCap).ifPresent(AirHopsCapability::addAirHop);
            this.setFallDistance(player);
            this.addExtraExhaustion(player);

            return true;

        }

        return false;

    }

    private boolean allowJump(PlayerEntity player, boolean sneaking) {

        boolean performingAction = player.onGround || player.isPassenger() || player.abilities.isFlying;
        boolean insideLiquid = player.isInWater() || player.isInLava();

        if (performingAction || insideLiquid) {
            return false;
        }

        ItemStack itemstack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        boolean fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemstack);

        if (ConfigBuildHandler.GENERAL_CONFIG.invertElytra.get()) {
            sneaking = !sneaking;
        }

        if (fallFlyingReady && !sneaking) {
            return false;
        }

        return player.abilities.allowFlying || !ConfigBuildHandler.GENERAL_CONFIG.disableOnHungry.get() || player.getFoodStats()
                .getFoodLevel() > ConfigBuildHandler.GENERAL_CONFIG.foodThreshold.get();

    }

    @SuppressWarnings("ConstantConditions")
    private int possibleJumps(PlayerEntity player) {

        return player.inventory.armorInventory.stream().mapToInt(itemStack -> EnchantmentHelper.getEnchantmentLevel(AirHopEnchantments.AIR_HOP, itemStack)).sum();

    }

    private void setFallDistance(PlayerEntity player) {

        float f = -1.25F;
        player.fallDistance = ConfigBuildHandler.GENERAL_CONFIG.resetFallDistance.get() ? f * player.getCapability(CapabilityHolder.airHopsCap)
                .map(AirHopsCapability::getAirHops).orElse(Integer.MAX_VALUE) : player.fallDistance + f;

    }

    private void addExtraExhaustion(PlayerEntity player) {

        float f = (float) Math.max(ConfigBuildHandler.GENERAL_CONFIG.hopExhaustion.get() - 1.0, 0);
        player.addExhaustion(player.isSprinting() ? 0.2F * f : 0.05F * f);

    }

}
