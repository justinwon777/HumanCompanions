package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.client.CompanionScreen;
import com.github.justinwon777.humancompanions.container.CompanionContainer;
import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import com.github.justinwon777.humancompanions.networking.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE =
            NetworkRegistry.newSimpleChannel(new ResourceLocation(HumanCompanions.MOD_ID,
                    "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, OpenInventoryPacket.class, OpenInventoryPacket::encode, OpenInventoryPacket::decode, OpenInventoryPacket::handle);
        INSTANCE.registerMessage(id++, SetAlertPacket.class, SetAlertPacket::encode, SetAlertPacket::decode,
                SetAlertPacket::handle);
        INSTANCE.registerMessage(id++, SetHuntingPacket.class, SetHuntingPacket::encode, SetHuntingPacket::decode,
                SetHuntingPacket::handle);
        INSTANCE.registerMessage(id++, SetPatrollingPacket.class, SetPatrollingPacket::encode, SetPatrollingPacket::decode,
                SetPatrollingPacket::handle);
        INSTANCE.registerMessage(id++, ClearTargetPacket.class, ClearTargetPacket::encode, ClearTargetPacket::decode,
                ClearTargetPacket::handle);
    }

    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
    public static void openInventory(OpenInventoryPacket packet) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            Entity entity = player.level.getEntity(packet.getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity) {
                AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                ClientPlayerEntity clientplayerentity = Minecraft.getInstance().player;
                CompanionContainer container = new CompanionContainer(packet.getId(), player.inventory, companion.inventory);
                clientplayerentity.containerMenu = container;
                Minecraft.getInstance().setScreen(new CompanionScreen(container, player.inventory, companion));
            }
        }
    }
}
