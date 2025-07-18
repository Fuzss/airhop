package fuzs.airhop.data.client;

import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.AIR_HOP_ENCHANTMENT, "Air Hop");
        builder.add(ModRegistry.AIR_HOP_ENCHANTMENT, "desc", "Enables jumping in mid-air.");
        builder.add(ModRegistry.ENTITY_PLAYER_HOP_SOUND_EVENT.value(), "Player hops");
    }
}
