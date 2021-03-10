package com.fuzs.puzzleslib_ah.element.side;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * implement this for elements with common capabilities
 */
public interface ICommonElement extends ISidedElement {

    /**
     * register common events
     */
    default void setupCommon() {

    }

    /**
     * setup for {@link net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent}
     */
    default void loadCommon() {

    }

    /**
     * build common config
     *
     * @param builder builder for common config
     */
    default void setupCommonConfig(ForgeConfigSpec.Builder builder) {

    }

    /**
     * @return description for this elements common config section
     */
    default String[] getCommonDescription() {

        return new String[0];
    }

}
