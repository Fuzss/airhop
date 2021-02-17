package com.fuzs.airhop.util;

import com.fuzs.airhop.capability.CapabilityController;
import com.fuzs.airhop.capability.container.IAirHops;
import com.fuzs.airhop.config.ConfigHandler;
import com.fuzs.airhop.handler.CapabilityHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;

import java.util.List;

public class JumpHelper {

    public static boolean doJump(EntityPlayer player, boolean sneaking) {

        if (!allowJump(player, sneaking)) {

            return false;
        }

        IAirHops capability = CapabilityController.getCapability(player, CapabilityController.AIR_HOPS_CAPABILITY);
        if (capability.getAirHops() < JumpHelper.possibleJumps(player)) {

            player.jump();
            capability.addAirHop();
            setFallDistance(player);
            addExtraExhaustion(player);

            return true;
        }

        return false;
    }

    private static boolean allowJump(EntityPlayer player, boolean sneaking) {

        boolean isPerformingAction = player.onGround || player.isRiding() || player.capabilities.isFlying;
        boolean isInsideLiquid = player.isInWater() || player.isInLava();
        if (isPerformingAction || isInsideLiquid) {

            return false;
        }

        ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        boolean fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ItemElytra.isUsable(itemstack);
        if (ConfigHandler.invertElytra) {

            sneaking = !sneaking;
        }

        if (fallFlyingReady && !sneaking) {

            return false;
        }

        return player.capabilities.allowFlying || !ConfigHandler.f1BlockOnHungry || player.getFoodStats().getFoodLevel() > ConfigHandler.foodThreshold;
    }

    private static int possibleJumps(EntityPlayer player) {

        EntityEquipmentSlot slot = ConfigHandler.enchantmentConfig.type.getSlot();
        int level;
        if (slot == null) {

            level = countLevels(player.inventory.armorInventory);
        } else {

            ItemStack stack = player.getItemStackFromSlot(slot);
            level = EnchantmentHelper.getEnchantmentLevel(CapabilityHandler.AIR_HOP_ENCHANTMENT, stack);
        }

        return level;
    }

    private static int countLevels(List<ItemStack> list) {

        int maxLevel = ConfigHandler.enchantmentConfig.maxLevel;

        return list.stream()
                .mapToInt(itemStack -> Math.min(maxLevel, EnchantmentHelper.getEnchantmentLevel(CapabilityHandler.AIR_HOP_ENCHANTMENT, itemStack)))
                .sum();

    }

    private static void setFallDistance(EntityPlayer player) {

        float fallDistanceFactor = -1.25F;
        IAirHops capability = CapabilityController.getCapability(player, CapabilityController.AIR_HOPS_CAPABILITY);
        if (ConfigHandler.resetFallDistance) {

            player.fallDistance = fallDistanceFactor * capability.getAirHops();
        } else {

            player.fallDistance = player.fallDistance + fallDistanceFactor;
        }
    }

    private static void addExtraExhaustion(EntityPlayer player) {

        float exhaustion = (float) Math.max(ConfigHandler.hopExhaustion - 1.0, 0);
        player.addExhaustion(player.isSprinting() ? 0.2F * exhaustion : 0.05F * exhaustion);
    }

}
