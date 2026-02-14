package net.minecraftforge.event;

import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;

/** Minimal compatibility shim for AddReloadListenerEvent used by data loaders */
public class AddReloadListenerEvent {
    public AddReloadListenerEvent() {}

    public void addListener(SimpleJsonResourceReloadListener listener) {
        // no-op compatibility shim; actual registration happens in real platform
    }
}
