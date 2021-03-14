package com.fuzs.airhop.element;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.AirHopsCapability;
import com.fuzs.airhop.capability.IAirHopsCapability;
import com.fuzs.airhop.enchantment.AirHopEnchantment;
import com.fuzs.airhop.mixin.accessor.ILivingEntityAccessor;
import com.fuzs.airhop.network.message.SSyncAirHopsMessage;
import com.fuzs.airhop.network.message.client.CAirHopMessage;
import com.fuzs.puzzleslib_ah.PuzzlesLib;
import com.fuzs.puzzleslib_ah.capability.CapabilityController;
import com.fuzs.puzzleslib_ah.capability.core.CapabilityDispatcher;
import com.fuzs.puzzleslib_ah.element.AbstractElement;
import com.fuzs.puzzleslib_ah.element.side.IClientElement;
import com.fuzs.puzzleslib_ah.element.side.ICommonElement;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("ConstantConditions")
public class AirHopElement extends AbstractElement implements ICommonElement, IClientElement {

    @ObjectHolder(AirHop.MODID + ":" + "air_hop")
    public static final Enchantment AIR_HOP_ENCHANTMENT = null;

    @ObjectHolder(AirHop.MODID + ":" + "entity.player.hop")
    public static final SoundEvent ENTITY_PLAYER_HOP_SOUND = null;

    @CapabilityInject(IAirHopsCapability.class)
    public static final Capability<AirHopsCapability> AIR_HOPS_CAPABILITY = null;

    // common config
    public boolean summonCloud;
    public boolean hopSound;
    public double damageChance;
    private boolean fallDamage;
    // client config
    private boolean fallingOnly;
    private boolean disableOnHungry;

    @Override
    public String getDescription() {

        return "A new enchantment for jumping multiple times. What more could you want in life?";
    }

    @Override
    public void setupCommon() {

        PuzzlesLib.getRegistryManager().register("air_hop", new AirHopEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.FEET));
        PuzzlesLib.getRegistryManager().register("entity.player.hop", new SoundEvent(new ResourceLocation(AirHop.MODID, "entity.player.hop")));
        this.addListener(this::onEntityJoinWorld);
        this.addListener(this::onLivingFall);
        this.addListener(this::onPlayerFall);
    }

    @Override
    public void setupClient() {

        this.addListener(this::onPlayerTick);
    }

    @Override
    public void initCommon() {

        PuzzlesLib.getNetworkHandler().registerMessage(SSyncAirHopsMessage::new, LogicalSide.CLIENT);
        PuzzlesLib.getNetworkHandler().registerMessage(CAirHopMessage::new, LogicalSide.SERVER);
        PuzzlesLib.getCapabilityController().addEntityCapability(new ResourceLocation(AirHop.MODID, "air_hops"), IAirHopsCapability.class, AirHopsCapability::new, entity -> {

            if (entity instanceof PlayerEntity) {

                return new CapabilityDispatcher<>(new AirHopsCapability(), AIR_HOPS_CAPABILITY);
            }

            return null;
        });
    }

    @Override
    public void setupCommonConfig(ForgeConfigSpec.Builder builder) {

        addToConfig(builder.comment("Spawn a small particle cloud at the players feet on every air hop.").define("Spawn Particle Cloud", true), v -> this.summonCloud = v);
        addToConfig(builder.comment("Play a funny sound effect whenever the player hops in mid-air.").define("Play Hop Sound", true), v -> this.hopSound = v);
        addToConfig(builder.comment("Chance the player's boots will be damaged by an air hop.").defineInRange("Boots Damage Chance", 0.4, 0.0, 1.0), v -> this.damageChance = v);
        addToConfig(builder.comment("Take normal fall damage when hitting the ground after air hopping.").define("Inflict Fall Damage", false), v -> this.fallDamage = v);
    }

    @Override
    public void setupClientConfig(ForgeConfigSpec.Builder builder) {

        addToConfig(builder.comment("Air hopping can only be used while falling to prevent gaining too much height.").define("Only When Falling", false), v -> this.fallingOnly = v);
        addToConfig(builder.comment("Prevent air hop enchantment from working when the player has 6 or less food points.").define("Disable On Hungry", true), v -> this.disableOnHungry = v);
    }

    private void onPlayerTick(final TickEvent.PlayerTickEvent evt) {
        
        if (evt.phase == TickEvent.Phase.END) {

            ILivingEntityAccessor playerAccessor = (ILivingEntityAccessor) evt.player;
            if (playerAccessor.getIsJumping() && playerAccessor.getJumpTicks() == 0 && this.attemptJump(evt.player)) {

                // prevent accidental usage of air hops
                playerAccessor.setJumpTicks(10);
                // trigger jump on server
                PuzzlesLib.getNetworkHandler().sendToServer(new CAirHopMessage());
            }
        }
    }

    private void onEntityJoinWorld(final EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof ServerPlayerEntity) {

            ServerPlayerEntity player = (ServerPlayerEntity) evt.getEntity();
            byte airHops = CapabilityController.getCapability(player, AIR_HOPS_CAPABILITY).map(AirHopsCapability::getAirHops).orElse((byte) 0);
            if (airHops > 0) {

                // capability is not synced automatically, so a client could potentially re-log to regain all their air hops
                PuzzlesLib.getNetworkHandler().sendTo(new SSyncAirHopsMessage(airHops), player);
            }
        }
    }

    private void onLivingFall(final LivingFallEvent evt) {

        if (evt.getEntityLiving() instanceof PlayerEntity) {

            evt.setDistance(this.onGroundHit((PlayerEntity) evt.getEntityLiving(), evt.getDistance()));
        }
    }

    private void onPlayerFall(final PlayerFlyableFallEvent evt) {

        evt.setDistance(this.onGroundHit(evt.getPlayer(), evt.getDistance()));
    }

    private boolean attemptJump(PlayerEntity player) {

        AirHopsCapability capability = CapabilityController.getCapability(player, AIR_HOPS_CAPABILITY).orElse(null);
        if (capability != null && this.canJump(player) && this.isSaturated(player)) {

            if (capability.getAirHops() < EnchantmentHelper.getMaxEnchantmentLevel(AIR_HOP_ENCHANTMENT, player)) {

                player.jump();
                player.fallDistance = 0.0F;
                capability.addAirHop();

                return true;
            }
        }

        return false;
    }

    private boolean canJump(PlayerEntity player) {

        boolean isAirborne = !player.onGround && (!this.fallingOnly || getJumpHeight(player) / 2.0F < player.fallDistance);
        boolean isPerformingAction = player.isPassenger() || player.abilities.isFlying || player.isOnLadder();

        return isAirborne && !isPerformingAction && !(player.isInWater() || player.isInLava());
    }

    private boolean isSaturated(PlayerEntity player) {

        // air hopping always works in creative mode
        return player.abilities.allowFlying || !this.disableOnHungry || player.getFoodStats().getFoodLevel() > 6;
    }

    private float onGroundHit(PlayerEntity player, float fallDistance) {

        AirHopsCapability capability = CapabilityController.getCapability(player, AIR_HOPS_CAPABILITY).orElse(null);
        if (capability != null) {

            byte airHops = capability.getAirHops();
            capability.resetAirHops();
            if (!this.fallDamage && airHops > 0) {

                return Math.max(0.0F, fallDistance - airHops * getJumpHeight(player));
            }
        }

        return fallDistance;
    }

    private static float getJumpHeight(PlayerEntity player) {

        float jumpHeight = 1.25F;
        if (player.isPotionActive(Effects.JUMP_BOOST)) {

            jumpHeight += 0.6875F * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1.0F);
        }

        return jumpHeight;
    }

}
