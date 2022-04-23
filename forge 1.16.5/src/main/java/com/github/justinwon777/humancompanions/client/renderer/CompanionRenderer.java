package com.github.justinwon777.humancompanions.client.renderer;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;

public class CompanionRenderer extends BipedRenderer<AbstractHumanCompanionEntity, PlayerModel<AbstractHumanCompanionEntity>> {

    public CompanionRenderer(EntityRendererManager context) {
        super(context, new PlayerModel<>(0.0F, false), 0.5f);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
    }

    public void render(AbstractHumanCompanionEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        this.setModelProperties(p_225623_1_);
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    private void setModelProperties(AbstractHumanCompanionEntity companion) {
        PlayerModel<AbstractHumanCompanionEntity> companionModel = this.getModel();
        
        BipedModel.ArmPose bipedmodel$armpose = getArmPose(companion, Hand.MAIN_HAND);
        BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(companion, Hand.OFF_HAND);
        if (bipedmodel$armpose.isTwoHanded()) {
            bipedmodel$armpose1 = companion.getOffhandItem().isEmpty() ? BipedModel.ArmPose.EMPTY : BipedModel.ArmPose.ITEM;
        }

        if (companion.getMainArm() == HandSide.RIGHT) {
            companionModel.rightArmPose = bipedmodel$armpose;
            companionModel.leftArmPose = bipedmodel$armpose1;
        } else {
            companionModel.rightArmPose = bipedmodel$armpose1;
            companionModel.leftArmPose = bipedmodel$armpose;
        }
    }

    private static BipedModel.ArmPose getArmPose(AbstractHumanCompanionEntity companion, Hand hand) {
        ItemStack itemstack = companion.getItemInHand(hand);
        if (itemstack.isEmpty()) {
            return BipedModel.ArmPose.EMPTY;
        } else {
            if (companion.getUsedItemHand() == hand && companion.getUseItemRemainingTicks() > 0) {
                UseAction useaction = itemstack.getUseAnimation();

                if (useaction == UseAction.BOW) {
                    return BipedModel.ArmPose.BOW_AND_ARROW;
                }

                if (useaction == UseAction.CROSSBOW && hand == companion.getUsedItemHand()) {
                    return BipedModel.ArmPose.CROSSBOW_CHARGE;
                }
            } else if (!companion.swinging && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
                return BipedModel.ArmPose.CROSSBOW_HOLD;
            }

            return BipedModel.ArmPose.ITEM;
        }
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractHumanCompanionEntity entity) {
        return entity.getResourceLocation();
    }

    protected void scale(AbstractHumanCompanionEntity p_117798_, MatrixStack p_117799_, float p_117800_) {
        float f = 0.9375F;
        p_117799_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}


