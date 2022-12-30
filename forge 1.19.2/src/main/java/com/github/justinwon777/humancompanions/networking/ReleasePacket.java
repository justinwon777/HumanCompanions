package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ReleasePacket {
    private final int entityId;

    public ReleasePacket(int entityId) {
        this.entityId = entityId;
    }

    public static ReleasePacket decode(FriendlyByteBuf buf) {
        return new ReleasePacket(buf.readInt());
    }

    public static void encode(ReleasePacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(ReleasePacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayer player = context.get().getSender();
                    if (player != null && player.level instanceof ServerLevel) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity companion) {
                            companion.release();
                            String message = companion.getDisplayName().getString().split(" ")[0] +
                                    " is no longer your companion.";
                            player.sendSystemMessage(Component.literal(message));
                        }
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
