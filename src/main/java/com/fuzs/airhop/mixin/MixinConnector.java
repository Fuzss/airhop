package com.fuzs.airhop.mixin;

import com.fuzs.airhop.AirHop;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

@SuppressWarnings("unused")
public class MixinConnector implements IMixinConnector {

    @Override
    public void connect() {

        Mixins.addConfiguration("META-INF/" + AirHop.MODID + ".mixins.json");
    }

}
