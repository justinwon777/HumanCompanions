package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetPatrolingPacket {
    private final int entityId;

    public SetPatrolingPacket(int entityId) {
        this.entityId = entityId;
    }

    public static SetPatrolingPacket decode(FriendlyByteBuf buf) {
        return new SetPatrolingPacket(buf.readInt());
    }

    public static void encode(SetPatrolingPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(SetPatrolingPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayer player = context.get().getSender();
                    if (player != null && player.level instanceof ServerLevel) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity) {
                            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                            if (companion.isFollowing()) {
                                companion.setPatrolling(true);
                                companion.setFollowing(false);
                                companion.setGuarding(false);
                                companion.setPatrolPos(companion.blockPosition());
                            } else if (companion.isPatrolling()) {
                                companion.setPatrolling(false);
                                companion.setFollowing(false);
                                companion.setGuarding(true);
                                companion.setPatrolPos(companion.blockPosition());
                            } else {
                                companion.setPatrolling(false);
                                companion.setFollowing(true);
                                companion.setGuarding(false);
                            }
                        }
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
