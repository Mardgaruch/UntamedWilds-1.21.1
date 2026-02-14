package net.minecraftforge.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Minimal event stub implementing a put method expected by mods during migration.
 * This stores attribute suppliers in a map keyed by entity type. Implementation is a no-op for runtime in this workspace.
 */
public class EntityAttributeCreationEvent {
    private final Map<EntityType<?>, AttributeSupplier> attributes = new ConcurrentHashMap<>();

    public <T extends Entity> void put(EntityType<T> type, AttributeSupplier supplier) {
        this.attributes.put(type, supplier);
    }

    public Map<EntityType<?>, AttributeSupplier> getAttributes() {
        return this.attributes;
    }
}
