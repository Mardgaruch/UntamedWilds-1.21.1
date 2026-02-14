package net.minecraftforge.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkRegistry {
    public static SimpleChannel newSimpleChannel(ResourceLocation name, java.util.function.Supplier<String> protocolSupplier, java.util.function.Predicate<String> acceptor, java.util.function.Predicate<String> verifier) {
        return new SimpleChannel();
    }
}
