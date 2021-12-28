package com.justin.companion.client.renderer;

import com.justin.companion.entity.NPC;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.model.geom.ModelLayers;

public class NPCRenderer extends HumanoidMobRenderer<NPC, PlayerModel<NPC>> {
    public NPCRenderer(Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
    }
}

