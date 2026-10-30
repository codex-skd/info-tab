package com.skd.infotab;

import com.skd.infotab.network.ActivityPingPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

/**
 * Detects mouse/keyboard/look input on the client while playing (no GUI open)
 * and pings the server so AFK status resets even without a world interaction
 * or a position change (e.g. just looking around).
 */
@EventBusSubscriber(modid = InfoTab.MODID, value = Dist.CLIENT)
public class AfkActivityClientHandler {

    private static final long PING_INTERVAL_MS = 3000;

    private static boolean inputDetected = false;
    private static float lastYaw = Float.NaN;
    private static float lastPitch = Float.NaN;
    private static long lastPingTime = 0;

    @SubscribeEvent
    public static void onMouseButton(InputEvent.MouseButton.Post event) {
        if (event.getAction() == 1) inputDetected = true;
    }

    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        if (event.getAction() == 1) inputDetected = true;
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.gui.screen() != null) {
            lastYaw = Float.NaN;
            lastPitch = Float.NaN;
            return;
        }

        if (!Float.isNaN(lastYaw) && (player.getYRot() != lastYaw || player.getXRot() != lastPitch)) {
            inputDetected = true;
        }
        lastYaw = player.getYRot();
        lastPitch = player.getXRot();

        if (!inputDetected) return;

        long now = System.currentTimeMillis();
        if (now - lastPingTime < PING_INTERVAL_MS) return;

        lastPingTime = now;
        inputDetected = false;
        ClientPacketDistributor.sendToServer(new ActivityPingPacket());
    }
}
