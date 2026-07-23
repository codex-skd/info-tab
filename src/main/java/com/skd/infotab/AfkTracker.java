package com.skd.infotab;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = InfoTab.MODID)
public class AfkTracker {

    private static final Map<ServerPlayer, Long> lastActivity = new ConcurrentHashMap<>();
    private static final Map<ServerPlayer, Vec3> lastPosition = new ConcurrentHashMap<>();
    private static int tickCounter = 0;

    public static boolean isAfk(ServerPlayer player) {
        Long last = lastActivity.get(player);
        if (last == null) {
            lastActivity.put(player, System.currentTimeMillis());
            lastPosition.put(player, player.position());
            return false;
        }
        long timeout = Config.AFK_TIMEOUT_MINUTES.get() * 60_000L;
        return System.currentTimeMillis() - last > timeout;
    }

    public static void updateActivity(ServerPlayer player) {
        lastActivity.put(player, System.currentTimeMillis());
        lastPosition.put(player, player.position());
    }

    public static void removePlayer(ServerPlayer player) {
        lastActivity.remove(player);
        lastPosition.remove(player);
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        tickCounter++;
        if (tickCounter % 100 != 0) return;
        for (var player : event.getServer().getPlayerList().getPlayers()) {
            Vec3 currentPos = player.position();
            Vec3 oldPos = lastPosition.get(player);
            if (oldPos != null && !oldPos.equals(currentPos)) {
                lastActivity.put(player, System.currentTimeMillis());
                lastPosition.put(player, currentPos);
            } else if (oldPos == null) {
                lastPosition.put(player, currentPos);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            updateActivity(sp);
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            updateActivity(sp);
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            updateActivity(sp);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            updateActivity(sp);
        }
    }

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            updateActivity(sp);
        }
    }

    @SubscribeEvent
    public static void onPlayerChat(ServerChatEvent event) {
        if (event.getPlayer() != null) {
            updateActivity(event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            removePlayer(sp);
        }
    }
}
