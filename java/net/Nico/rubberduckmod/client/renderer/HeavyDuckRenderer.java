package net.Nico.rubberduckmod.client.renderer;

import net.Nico.rubberduckmod.RubberDuckMod;
import net.Nico.rubberduckmod.client.model.HeavyDuckEntityModel;
import net.Nico.rubberduckmod.entity.HeavyDuckEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HeavyDuckRenderer extends MobRenderer<HeavyDuckEntity, HeavyDuckEntityModel<HeavyDuckEntity>> {

private static final ResourceLocation TEXTURE =
        new ResourceLocation(RubberDuckMod.MOD_ID,"textures/entity/heavyduck.png");

    public HeavyDuckRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HeavyDuckEntityModel<>(pContext.bakeLayer(HeavyDuckEntityModel.HeavyDuck)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(HeavyDuckEntity pEntity) {
        return TEXTURE;
    }


}

