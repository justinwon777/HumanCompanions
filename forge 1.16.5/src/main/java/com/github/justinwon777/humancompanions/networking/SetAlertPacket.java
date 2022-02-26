package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SetAlertPacket {
    private final int entityId;

    public SetAlertPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetAlertPacket decode(PacketBuffer buf) {
        return new SetAlertPacket(buf.readInt());
    }

    public static void encode(SetAlertPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetAlertPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayerEntity player = context.get().getSender();
                    if (player != null && player.level instanceof ServerWorld) {
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
