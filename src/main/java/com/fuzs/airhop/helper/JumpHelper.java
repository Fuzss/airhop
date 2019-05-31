package com.fuzs.airhop.helper;

import com.fuzs.airhop.capability.PlayerProperties;
import com.fuzs.airhop.handler.ConfigHandler;
import com.fuzs.airhop.handler.RegistryEventHandler;
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

        int jumps = PlayerProperties.getPlayerAirJumps(player).getAirJumps();

        if (jumps < JumpHelper.possibleJumps(player)) {

            player.jump();
            PlayerProperties.getPlayerAirJumps(player).addAirJump();
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

        if (ConfigHandler.f1BlockOnHungry && (player.getFoodStats().getFoodLevel() <= ConfigHandler.foodThreshold || !player.capabilities.allowFlying)) {
            return false;
        }

        return true;

    }

    private static int possibleJumps(EntityPlayer player) {

        EntityEquipmentSlot slot = ConfigHandler.enchantmentConfig.type.getSlot();
        int level;

        if (slot == null) {
            level = sumLevels(player.inventory.armorInventory);
        } else {
            ItemStack stack = player.getItemStackFromSlot(slot);
            level = EnchantmentHelper.getEnchantmentLevel(RegistryEventHandler.AIR_HOP, stack);
        }

        return level;

    }

    private static int sumLevels(List<ItemStack> list) {

        return list.stream().mapToInt(itemStack -> Math.min(ConfigHandler.enchantmentConfig.maxLevel, EnchantmentHelper
                .getEnchantmentLevel(RegistryEventHandler.AIR_HOP, itemStack))).sum();

    }

    private static void setFallDistance(EntityPlayer player) {

        float f = -1.25F;
        player.fallDistance = ConfigHandler.resetFallDistance ? f * PlayerProperties.getPlayerAirJumps(player).getAirJumps()
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
