package com.fuzs.airhop.handler;

import com.fuzs.airhop.helper.JumpHelper;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.AirHopMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class ClientHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent evt) {

        Minecraft mc = Minecraft.getInstance();

        if (!mc.isGameFocused() || evt.getAction() != GLFW_PRESS) {
            return;
        }

        // can't use player.movementInput.jump as it triggers too often
        if(mc.gameSettings.keyBindJump.isKeyDown()) {

            ClientPlayerEntity player = mc.player;
            if (JumpHelper.doJump(player, player.movementInput.sneak)) {
                NetworkHandler.sendToServer(new AirHopMessage(new AirHopMessage.AirHopMessageData(0)));
            }


        }

    }

}
