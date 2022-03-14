package com.github.justinwon777.humancompanions.networking;

import com.github.justinwon777.humancompanions.core.PacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenInventoryPacket {
    private final int id;
    private final int size;
    private final int entityId;

    public OpenInventoryPacket(int id, int size, int entityId) {
        this.id = id;
        this.size = size;
        this.entityId = entityId;
    }

    public static OpenInventoryPacket decode(PacketBuffer buf) {
        return new OpenInventoryPacket(buf.readUnsignedByte(), buf.readVarInt(), buf.readInt());
    }

    public static void encode(OpenInventoryPacket msg, PacketBuffer buf) {
        buf.writeByte(msg.id);
        buf.writeVarInt(msg.size);
        buf.writeInt(msg.entityId);
    }

    public int getId() {
        return this.id;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static void handle(OpenInventoryPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            PacketHandler.openInventory(msg);
        });
        context.get().setPacketHandled(true);
    }
}
