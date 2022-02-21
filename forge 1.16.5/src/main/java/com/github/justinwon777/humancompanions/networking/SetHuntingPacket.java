package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.HumanCompanionEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SetHuntingPacket {
    private final int entityId;

    public SetHuntingPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetHuntingPacket decode(PacketBuffer buf) {
        return new SetHuntingPacket(buf.readInt());
    }

    public static void encode(SetHuntingPacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetHuntingPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayerEntity player = context.get().getSender();
                    if (player != null && player.level instanceof ServerWorld) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof HumanCompanionEntity) {
                            HumanCompanionEntity companion = (HumanCompanionEntity) entity;
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
