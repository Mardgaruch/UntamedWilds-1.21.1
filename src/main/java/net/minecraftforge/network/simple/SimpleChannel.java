package net.minecraftforge.network.simple;

import java.util.function.Function;

public class SimpleChannel {

    public <T> MessageBuilder<T> messageBuilder(Class<T> type, int id) {
        return new MessageBuilder<>();
    }

    public void sendTo(Object packet, Object connection, Object direction) {
        // no-op for compatibility
    }

    public void sendToServer(Object packet) {
        // no-op for compatibility
    }

    public static class MessageBuilder<T> {
        public MessageBuilder<T> encoder(java.util.function.BiConsumer<T, net.minecraft.network.FriendlyByteBuf> enc) { return this; }
        public MessageBuilder<T> decoder(Function<net.minecraft.network.FriendlyByteBuf, T> dec) { return this; }
        public MessageBuilder<T> consumer(java.util.function.BiConsumer<T, java.util.function.Supplier<net.minecraftforge.network.NetworkEvent.Context>> cons) { return this; }
        public void add() { }
    }
}
