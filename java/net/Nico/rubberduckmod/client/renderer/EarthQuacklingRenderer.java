package net.Nico.rubberduckmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.Nico.rubberduckmod.RubberDuckMod;
import net.Nico.rubberduckmod.client.model.HeavyDuckEntityModel;
import net.Nico.rubberduckmod.entity.earthquackling;
import net.Nico.rubberduckmod.entity.firequackling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EarthQuacklingRenderer extends MobRenderer<earthquackling, HeavyDuckEntityModel<earthquackling>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RubberDuckMod.MOD_ID, "textures/entity/earthquackling.png");

    public EarthQuacklingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HeavyDuckEntityModel<>(pContext.bakeLayer(HeavyDuckEntityModel.HeavyDuck)), 0.1F); // The 0.1F is the shadow size
    }

    @Override
    public ResourceLocation getTextureLocation(earthquackling pEntity) {
        return TEXTURE;
    }

    @Override
    protected void scale(earthquackling earthquackling, PoseStack poseStack, float partialTickTime) {
        float scaleFactor = 0.5F; // Adjust this scale factor for the Quackling size
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.scale(earthquackling, poseStack, partialTickTime);
    }


}
