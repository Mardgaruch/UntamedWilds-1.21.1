package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelKingCrab;
import untamedwilds.entity.arthropod.EntityKingCrab;

public class RendererKingCrab extends MobRenderer<EntityKingCrab, ModelKingCrab> {

    public RendererKingCrab(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelKingCrab(), 0.2F);
    }

    @Override
    protected void scale(EntityKingCrab entity, PoseStack matrixStackIn, float partialTickTime) {
        float f = entity.getMobSize() * 0.7F;
        f *= entity.getScale();
        matrixStackIn.scale(f, f, f);
        this.shadowRadius = f * 0.6F;
    }

    public @NotNull ResourceLocation getTextureLocation(EntityKingCrab entity) {
        return entity.getTexture();
    }
}
