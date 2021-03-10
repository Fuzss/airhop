package com.fuzs.puzzleslib_ah.element.side;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * implement this for elements with server-side capabilities
 */
public interface IServerElement extends ISidedElement {

    /**
     * register server events
     */
    default void setupServer() {

    }

    /**
     * setup for {@link net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent}
     */
    default void loadServer() {

    }

    /**
     * build server config
     *
     * @param builder builder for server config
     */
    default void setupServerConfig(ForgeConfigSpec.Builder builder) {

    }

    /**
     * @return description for this elements server config section
     */
    default String[] getServerDescription() {

        return new String[0];
    }

}
