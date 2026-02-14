package net.minecraftforge.data.event;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Minimal compatibility stub for the data gathering event.
 */
public class GatherDataEvent {
    private final DataGenerator generator;
    private final ExistingFileHelper helper;
    private final boolean includeServer;

    public GatherDataEvent(DataGenerator generator, ExistingFileHelper helper, boolean includeServer) {
        this.generator = generator;
        this.helper = helper;
        this.includeServer = includeServer;
    }

    public DataGenerator getGenerator() { return generator; }
    public ExistingFileHelper getExistingFileHelper() { return helper; }
    public boolean includeServer() { return includeServer; }
}
