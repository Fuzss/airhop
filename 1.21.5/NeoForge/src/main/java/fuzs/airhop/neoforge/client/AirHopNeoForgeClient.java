package fuzs.airhop.neoforge.client;

import fuzs.airhop.AirHop;
import fuzs.airhop.client.AirHopClient;
import fuzs.airhop.data.client.ModLanguageProvider;
import fuzs.airhop.neoforge.data.client.ModSoundDefinitionProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = AirHop.MOD_ID, dist = Dist.CLIENT)
public class AirHopNeoForgeClient {

    public AirHopNeoForgeClient() {
        ClientModConstructor.construct(AirHop.MOD_ID, AirHopClient::new);
        DataProviderHelper.registerDataProviders(AirHop.MOD_ID, ModLanguageProvider::new,
                ModSoundDefinitionProvider::new
        );
    }
}
