package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetHuntingPacket {
    private final int entityId;

    public SetHuntingPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetHuntingPacket decode(FriendlyByteBuf buf) {
        return new SetHuntingPacket(buf.readInt());
    }

    public static void encode(SetHuntingPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetHuntingPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayer player = context.get().getSender();
                    if (player != null && player.level instanceof ServerLevel) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity) {
                            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                            companion.setHunting(!companion.isHunting());
                            if (companion.isHunting()) {
                                companion.addHuntingGoals();
                            } else {
                                companion.removeHuntingGoals();
                            }
                        }
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
