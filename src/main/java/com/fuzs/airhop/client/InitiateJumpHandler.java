package com.fuzs.airhop.client;

import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.message.SimpleHopMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

@OnlyIn(Dist.CLIENT)
public class InitiateJumpHandler {

    private final Minecraft mc = Minecraft.getInstance();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent evt) {

        if (!this.mc.isGameFocused() || evt.getAction() != GLFW_PRESS) {

            return;
        }

        // can't use player.movementInput.jump as it triggers too often
        if(this.mc.gameSettings.keyBindJump.isKeyDown()) {

            ClientPlayerEntity player = this.mc.player;
            if (player != null && SimpleHopMessage.JUMP_HELPER.doJump(player, player.movementInput.sneaking)) {

                NetworkHandler.getInstance().sendToServer(new SimpleHopMessage());
            }
        }
    }

}
