package com.harby.halocraft.Message;

import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HaloKeys {
    public int mountId;
    public int playerId;
    public int type;

    public HaloKeys(int mountId, int playerId, int type) {
        this.mountId = mountId;
        this.playerId = playerId;
        this.type = type;
    }
    public HaloKeys(int playerId, int type) {
        this.playerId = playerId;
        this.type = type;
    }


    public HaloKeys() {
    }

    public static HaloKeys read(FriendlyByteBuf buf) {
        return new HaloKeys(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public static void write(HaloKeys message, FriendlyByteBuf buf) {
        buf.writeInt(message.mountId);
        buf.writeInt(message.playerId);
        buf.writeInt(message.type);
    }

    public static void handle(HaloKeys message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Player playerSided = context.get().getSender();
            Entity parent = playerSided.level().getEntity(message.mountId);
            Entity keyPresser = playerSided.level().getEntity(message.playerId);
            if (keyPresser != null && parent instanceof BasicVehicleEntity mount && keyPresser instanceof Player && keyPresser.isPassengerOfSameVehicle(parent)) {
                mount.onKeyPacket(keyPresser, message.type);
            }
        });
        context.get().setPacketHandled(true);
    }
}

