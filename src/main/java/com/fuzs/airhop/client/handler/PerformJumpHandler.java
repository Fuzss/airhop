package com.fuzs.airhop.client.handler;

import com.fuzs.airhop.util.JumpHelper;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.MessageDoAirJump;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class PerformJumpHandler {
    
    private final Minecraft mc = Minecraft.getMinecraft();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent evt) {

        // repeat events are enabled on join world by default which shouldn't really be the case
        if (this.mc.currentScreen == null) {
            
            Keyboard.enableRepeatEvents(false);
        }

        // can't use player.movementInput.jump as it triggers too often
        if(this.mc.inGameHasFocus && this.mc.gameSettings.keyBindJump.isKeyDown()) {

            if (JumpHelper.doJump(this.mc.player, this.mc.player.movementInput.sneak)) {
                
                NetworkHandler.sendToServer(new MessageDoAirJump());
            }
        }
    }

}
