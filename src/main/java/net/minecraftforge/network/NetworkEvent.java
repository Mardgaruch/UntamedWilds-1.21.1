package net.minecraftforge.network;

import java.util.function.Supplier;

public class NetworkEvent {
    public static class Context {
        public void enqueueWork(Runnable runnable) {
            // In absence of a real network context during compile-time, execute immediately
            runnable.run();
        }
    }
}
