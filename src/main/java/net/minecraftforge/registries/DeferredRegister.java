package net.minecraftforge.registries;

import java.util.function.Supplier;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes","unchecked"})
public class DeferredRegister<T> {
    private final Map<String, RegistryObject<T>> registry = new HashMap<>();

    public static <T> DeferredRegister<T> create(Object registryKey, String modid) {
        return new DeferredRegister<>();
    }

    public RegistryObject<T> register(String name, Supplier<? extends T> supplier) {
        RegistryObject<T> ro = new RegistryObject<>(supplier.get());
        registry.put(name, ro);
        return ro;
    }
}
