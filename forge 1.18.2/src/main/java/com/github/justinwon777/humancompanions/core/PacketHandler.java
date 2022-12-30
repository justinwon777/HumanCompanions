package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.client.CompanionScreen;
import com.github.justinwon777.humancompanions.container.CompanionContainer;
import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import com.github.justinwon777.humancompanions.networking.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

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
        INSTANCE.registerMessage(id++, SetPatrolingPacket.class, SetPatrolingPacket::encode, SetPatrolingPacket::decode,
                SetPatrolingPacket::handle);
        INSTANCE.registerMessage(id++, ClearTargetPacket.class, ClearTargetPacket::encode, ClearTargetPacket::decode,
                ClearTargetPacket::handle);
        INSTANCE.registerMessage(id++, SetStationeryPacket.class, SetStationeryPacket::encode, SetStationeryPacket::decode,
                SetStationeryPacket::handle);
        INSTANCE.registerMessage(id++, ReleasePacket.class, ReleasePacket::encode, ReleasePacket::decode,
                ReleasePacket::handle);
    }

    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
    public static void openInventory(OpenInventoryPacket packet) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Entity entity = player.level.getEntity(packet.getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity) {
                AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                LocalPlayer clientplayerentity = Minecraft.getInstance().player;
                CompanionContainer container = new CompanionContainer(packet.getId(), player.getInventory(), companion.inventory);
                clientplayerentity.containerMenu = container;
                Minecraft.getInstance().setScreen(new CompanionScreen(container, player.getInventory(), companion));
            }
        }
    }
}
