package com.fuzs.airhop.helper;

import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.handler.ConfigHandler;
import com.fuzs.airhop.handler.RegistryHandler;
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

        int jumps = CapabilityHolder.getAirHopsCap(player).getAirHops();

        if (jumps < JumpHelper.possibleJumps(player)) {

            player.jump();
            CapabilityHolder.getAirHopsCap(player).addAirHop();
            setFallDistance(player);
            extraExhaustion(player);

            return true;

        }

        return false;

    }

    private static boolean allowJump(EntityPlayer player, boolean sneaking) {

        boolean performingAction = player.onGround || player.isRiding() || player.capabilities.isFlying;
        boolean insideLiquid = player.isInWater() || player.isInLava();

        if (performingAction || insideLiquid) {
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
            level = sumLevels(player.inventory.armorInventory);
        } else {
            ItemStack stack = player.getItemStackFromSlot(slot);
            level = EnchantmentHelper.getEnchantmentLevel(RegistryHandler.AIR_HOP_ENCH, stack);
        }

        return level;

    }

    private static int sumLevels(List<ItemStack> list) {

        return list.stream().mapToInt(itemStack -> Math.min(ConfigHandler.enchantmentConfig.maxLevel, EnchantmentHelper
                .getEnchantmentLevel(RegistryHandler.AIR_HOP_ENCH, itemStack))).sum();

    }

    private static void setFallDistance(EntityPlayer player) {

        float f = -1.25F;
        player.fallDistance = ConfigHandler.resetFallDistance ? f * CapabilityHolder.getAirHopsCap(player).getAirHops()
                : player.fallDistance + f;

    }

    private static void extraExhaustion(EntityPlayer player) {

        float f = (float) Math.max(ConfigHandler.hopExhaustion - 1.0, 0);

        if (player.isSprinting())
        {
            player.addExhaustion(0.2F * f);
        }
        else
        {
            player.addExhaustion(0.05F * f);
        }

    }

}
