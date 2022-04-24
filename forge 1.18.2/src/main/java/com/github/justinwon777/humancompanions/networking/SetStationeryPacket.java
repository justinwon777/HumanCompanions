package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetStationeryPacket {
    private final int entityId;

    public SetStationeryPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetStationeryPacket decode(FriendlyByteBuf buf) {
        return new SetStationeryPacket(buf.readInt());
    }

    public static void encode(SetStationeryPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetStationeryPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayer player = context.get().getSender();
                    if (player != null && player.level instanceof ServerLevel) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity) {
                            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                            companion.setStationery(!companion.isStationery());
                        }
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
