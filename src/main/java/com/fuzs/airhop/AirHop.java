package com.fuzs.airhop;

import com.fuzs.puzzleslib_ah.PuzzlesLib;
import com.fuzs.puzzleslib_ah.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod(AirHop.MODID)
public class AirHop extends PuzzlesLib {

    public static final String MODID = "airhop";
    public static final String NAME = "Air Hop";
    public static final Logger LOGGER = LogManager.getLogger(AirHop.NAME);

    public AirHop() {

        super();
        AirHopElements.setup(MODID);
        ConfigManager.get().load();
    }

}
