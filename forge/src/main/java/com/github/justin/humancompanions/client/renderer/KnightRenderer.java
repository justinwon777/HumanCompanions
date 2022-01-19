package com.github.justin.humancompanions.client.renderer;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.entity.AbstractHumanCompanionEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class KnightRenderer extends HumanoidMobRenderer<AbstractHumanCompanionEntity, PlayerModel<AbstractHumanCompanionEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities" +
            "/knight.png");

    public KnightRenderer(Context context) {
        super(context, new PlayerModel(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
        }

    @Override
    public ResourceLocation getTextureLocation(AbstractHumanCompanionEntity entity) {
        return TEXTURE;
    }

    protected void scale(AbstractHumanCompanionEntity p_117798_, PoseStack p_117799_, float p_117800_) {
        float f = 0.9375F;
        p_117799_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}

