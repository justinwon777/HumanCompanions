package com.github.justinwon777.humancompanions.client.renderer;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;


public class CompanionRenderer extends HumanoidMobRenderer<AbstractHumanCompanionEntity, PlayerModel<AbstractHumanCompanionEntity>> {

    public CompanionRenderer(Context context) {
        super(context, new PlayerModel(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
                context.getModelManager()
        ));
    }

    public void render(AbstractHumanCompanionEntity companion, float companionYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        this.setModelProperties(companion);
        super.render(companion, companionYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    private void setModelProperties(AbstractHumanCompanionEntity companion) {
        PlayerModel<AbstractHumanCompanionEntity> companionModel = this.getModel();
        HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(companion, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(companion, InteractionHand.OFF_HAND);

        if (companion.getMainArm() == HumanoidArm.RIGHT) {
            companionModel.rightArmPose = humanoidmodel$armpose;
            companionModel.leftArmPose = humanoidmodel$armpose1;
        } else {
            companionModel.rightArmPose = humanoidmodel$armpose1;
            companionModel.leftArmPose = humanoidmodel$armpose;
        }
    }

    private static HumanoidModel.ArmPose getArmPose(AbstractHumanCompanionEntity companion, InteractionHand hand) {
        ItemStack itemstack = companion.getItemInHand(hand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (companion.getUsedItemHand() == hand && companion.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.CROSSBOW && hand == companion.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }
            } else if (!companion.swinging && itemstack.is(Items.CROSSBOW) && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

            return HumanoidModel.ArmPose.ITEM;
        }
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractHumanCompanionEntity entity) {
        return entity.getResourceLocation();
    }

    protected void scale(AbstractHumanCompanionEntity p_117798_, PoseStack p_117799_, float p_117800_) {
        float f = 0.9375F;
        p_117799_.scale(f, f, f);
    }
}

