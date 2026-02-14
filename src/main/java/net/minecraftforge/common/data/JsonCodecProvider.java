package net.minecraftforge.common.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Minimal compatibility shim for JsonCodecProvider used in data generation.
 * This implementation is intentionally minimal: it provides a provider that
 * registers nothing. It exists to allow compilation and can be replaced with
 * a full implementation later when needed.
 */
public class JsonCodecProvider<T> implements DataProvider {

    public static <T> JsonCodecProvider<T> forDatapackRegistry(DataGenerator gen, ExistingFileHelper helper, String modId, RegistryOps<JsonElement> ops, Object key, Map<ResourceLocation, T> map) {
        return new JsonCodecProvider<>();
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        // No-op minimal implementation - return completed future to satisfy DataProvider contract
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public String getName() {
        return "JsonCodecProvider (compat stub)";
    }
}
