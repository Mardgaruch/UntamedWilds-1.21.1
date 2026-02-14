package untamedwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import untamedwilds.client.model.ModelBoar;
import untamedwilds.client.model.ModelBoarPiglet;
import untamedwilds.client.model.ModelWarthog;
import untamedwilds.entity.mammal.EntityBoar;

public class RendererBoar extends MobRenderer<EntityBoar, EntityModel<EntityBoar>> {

    private final ModelBoar BOAR_MODEL;
    private final ModelWarthog WARTHOG_MODEL;
    private final ModelBoarPiglet BOAR_MODEL_PIGLET;

    public RendererBoar(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelBoar(), 0.4F);
        this.BOAR_MODEL = (ModelBoar)this.getModel();
        this.WARTHOG_MODEL = new ModelWarthog();
        this.BOAR_MODEL_PIGLET = new ModelBoarPiglet();
    }

    @Override
    public void render(EntityBoar entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        model = entityIn.isBaby() ? BOAR_MODEL_PIGLET : entityIn.isWarthog() ? WARTHOG_MODEL : BOAR_MODEL ;
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    protected void scale(EntityBoar entity, PoseStack matrixStackIn, float partialTickTime) {
        float f = entity.getMobSize();
        f *= entity.isBaby() ? 0.8F : 1.0F;
        matrixStackIn.scale(f, f, f);
        this.shadowRadius = f * 0.6F;
    }

    public @NotNull ResourceLocation getTextureLocation(EntityBoar entity) {
        return entity.getTexture();
    }
}
