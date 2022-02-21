package com.github.justinwon777.humancompanions.client.renderer;

import com.github.justinwon777.humancompanions.entity.HumanCompanionEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class HumanCompanionRenderer extends BipedRenderer<HumanCompanionEntity, PlayerModel<HumanCompanionEntity>> {

    public HumanCompanionRenderer(EntityRendererManager context) {
        super(context, new PlayerModel<>(0.0F, false), 0.5f);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
    }

    @Override
    public ResourceLocation getTextureLocation(HumanCompanionEntity entity) {
        return entity.getResourceLocation();
    }

    protected void scale(HumanCompanionEntity p_117798_, MatrixStack p_117799_, float p_117800_) {
        float f = 0.9375F;
        p_117799_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}


