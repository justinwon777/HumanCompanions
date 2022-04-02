package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetAlertPacket {
    private final int entityId;

    public SetAlertPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetAlertPacket decode(FriendlyByteBuf buf) {
        return new SetAlertPacket(buf.readInt());
    }

    public static void encode(SetAlertPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetAlertPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayer player = context.get().getSender();
                    if (player != null && player.level instanceof ServerLevel) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity) {
                            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                            companion.setAlert(!companion.isAlert());
                            if (companion.isAlert()) {
                                companion.addAlertGoals();
                            } else {
                                companion.removeAlertGoals();
                            }
                        }
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
