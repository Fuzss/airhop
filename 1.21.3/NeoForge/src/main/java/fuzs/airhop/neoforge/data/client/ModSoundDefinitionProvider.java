package fuzs.airhop.neoforge.data.client;

import fuzs.airhop.AirHop;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.neoforge.api.data.v2.client.AbstractSoundDefinitionProvider;
import fuzs.puzzleslib.neoforge.api.data.v2.core.NeoForgeDataProviderContext;

public class ModSoundDefinitionProvider extends AbstractSoundDefinitionProvider {

    public ModSoundDefinitionProvider(NeoForgeDataProviderContext context) {
        super(context);
    }

    @Override
    public void addSoundDefinitions() {
        this.add(ModRegistry.ENTITY_PLAYER_HOP_SOUND_EVENT.value(), sound(AirHop.id("entity/player/hop1")).volume(0.2F),
                sound(AirHop.id("entity/player/hop2")).volume(0.2F)
        );
    }
}
