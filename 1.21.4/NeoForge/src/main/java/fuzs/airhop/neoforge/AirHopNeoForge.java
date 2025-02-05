package fuzs.airhop.neoforge;

import fuzs.airhop.AirHop;
import fuzs.airhop.data.ModEnchantmentRegistryProvider;
import fuzs.airhop.data.ModEnchantmentTagProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(AirHop.MOD_ID)
public class AirHopNeoForge {

    public AirHopNeoForge() {
        ModConstructor.construct(AirHop.MOD_ID, AirHop::new);
        DataProviderHelper.registerDataProviders(AirHop.MOD_ID, ModEnchantmentRegistryProvider::new,
                ModEnchantmentTagProvider::new
        );
    }
}
