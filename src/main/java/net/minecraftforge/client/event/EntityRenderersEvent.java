package net.minecraftforge.client.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EntityRenderersEvent {
    public static class RegisterRenderers {
        public <T extends Entity> void registerEntityRenderer(EntityType<T> type, EntityRendererProvider<? super T> provider) {
            // stub: no-op for compilation; actual registrations happen at runtime in the platform
        }
    }
}
