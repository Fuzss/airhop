package com.fuzs.airhop.client;

import com.fuzs.airhop.common.helper.PerformJumpHelper;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.message.AirHopMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class InitiateJumpHandler {

    private final Minecraft mc = Minecraft.getInstance();
    private final PerformJumpHelper jumpHelper = new PerformJumpHelper();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent evt) {

        if (!this.mc.isGameFocused() || evt.getAction() != GLFW_PRESS) {
            return;
        }

        // can't use player.movementInput.jump as it triggers too often
        if(this.mc.gameSettings.keyBindJump.isKeyDown()) {

            ClientPlayerEntity player = this.mc.player;
            if (player != null && this.jumpHelper.doJump(player, player.movementInput.sneak)) {
                NetworkHandler.sendToServer(new AirHopMessage(new AirHopMessage.AirHopMessageData(0)));
            }


        }

    }

}
