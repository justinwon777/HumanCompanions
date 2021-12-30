package com.github.justin.companions.client.renderer;

import com.github.justin.companions.Companions;
import com.github.justin.companions.entity.CompanionEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CompanionRenderer<Type extends CompanionEntity> extends MobRenderer<Type, PlayerModel<Type>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Companions.MOD_ID, "textures/entities" +
            "/companion.png");

    public CompanionRenderer(Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

}

