package fuzs.airhop.neoforge.data.client;

import fuzs.airhop.AirHop;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.neoforge.api.client.data.v2.AbstractSoundProvider;

public class ModSoundDefinitionProvider extends AbstractSoundProvider {

    public ModSoundDefinitionProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addSounds() {
        this.add(ModRegistry.ENTITY_PLAYER_HOP_SOUND_EVENT.value(),
                sound(AirHop.id("entity/player/hop1")).volume(0.2F),
                sound(AirHop.id("entity/player/hop2")).volume(0.2F));
    }
}
