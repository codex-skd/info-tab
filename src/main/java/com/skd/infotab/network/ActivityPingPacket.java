package com.skd.infotab.network;

import com.skd.infotab.InfoTab;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ActivityPingPacket() implements CustomPacketPayload {

    public static final Type<ActivityPingPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(InfoTab.MODID, "activity_ping"));
    public static final StreamCodec<FriendlyByteBuf, ActivityPingPacket> STREAM_CODEC = StreamCodec.unit(new ActivityPingPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
