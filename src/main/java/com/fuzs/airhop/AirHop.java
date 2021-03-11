package com.fuzs.airhop;

import com.fuzs.airhop.element.AirHopElement;
import com.fuzs.puzzleslib_ah.PuzzlesLib;
import com.fuzs.puzzleslib_ah.config.ConfigManager;
import com.fuzs.puzzleslib_ah.element.AbstractElement;
import com.fuzs.puzzleslib_ah.element.registry.ElementRegistry;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod(AirHop.MODID)
public class AirHop extends PuzzlesLib {

    public static final String MODID = "airhop";
    public static final String NAME = "Air Hop";
    public static final Logger LOGGER = LogManager.getLogger(AirHop.NAME);

    public static final AbstractElement AIR_HOP = register("air_hop",AirHopElement::new);

    public AirHop() {

        super();
        ElementRegistry.setup(MODID);
        ConfigManager.get().load();
    }

}
