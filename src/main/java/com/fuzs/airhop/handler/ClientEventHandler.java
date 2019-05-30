package com.fuzs.airhop.handler;

import com.fuzs.airhop.helper.JumpHelper;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.MessageAirJump;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("unused")
public class ClientEventHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent evt) {

        Minecraft mc = Minecraft.getMinecraft();

        // repeat events are enabled on join world by default which shouldn't really be the case
        if (mc.currentScreen == null) {
            Keyboard.enableRepeatEvents(false);
        }

        EntityPlayerSP player = mc.player;
        // can't use player.movementInput.jump as it triggers too often
        boolean down = mc.gameSettings.keyBindJump.isKeyDown();

        if(mc.inGameHasFocus && down) {

            if (JumpHelper.doJump(player, player.movementInput.sneak)) {
                JumpHelper.setFallDistance(player);
                NetworkHandler.sendToServer(new MessageAirJump());
            }


        }

    }

}
