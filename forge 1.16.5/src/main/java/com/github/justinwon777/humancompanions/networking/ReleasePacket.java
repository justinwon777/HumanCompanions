package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ReleasePacket {
    private final int entityId;

    public ReleasePacket(int entityId) {
        this.entityId = entityId;
    }

    public static ReleasePacket decode(PacketBuffer buf) {
        return new ReleasePacket(buf.readInt());
    }

    public static void encode(ReleasePacket msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(ReleasePacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (msg != null) {
                context.get().enqueueWork(() -> {
                    ServerPlayerEntity player = context.get().getSender();
                    if (player != null && player.level instanceof ServerWorld) {
                        Entity entity = player.level.getEntity(msg.getEntityId());
                        if (entity instanceof AbstractHumanCompanionEntity) {
                            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                            companion.release();
                            String message = companion.getDisplayName().getString().split(" ")[0] +
                                    " is no longer your companion.";
                            player.sendMessage(new StringTextComponent(message), companion.getUUID());
                        }
                    }
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
