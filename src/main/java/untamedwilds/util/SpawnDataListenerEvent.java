package untamedwilds.util;

import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import untamedwilds.UntamedWilds;
import untamedwilds.world.FaunaHandler;

@EventBusSubscriber(modid = UntamedWilds.MOD_ID)
public class SpawnDataListenerEvent {

    public static final JSONLoader<SpawnDataHolder> SPAWN_DATA_HOLDER = new JSONLoader<>("spawn_tables", SpawnDataHolder.CODEC);

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        // Reset cached spawn entries before datapack data is applied to avoid stale/duplicate entries.
        FaunaHandler.clearAllSpawnLists();
        event.addListener(SPAWN_DATA_HOLDER);
    }
}
