package com.skd.infotab;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = InfoTab.MODID)
public class AfkTracker {

    private static final long AFK_TIMEOUT = 600_000;
    private static final Map<ServerPlayer, Long> lastActivity = new ConcurrentHashMap<>();
    private static final Map<ServerPlayer, Vec3> lastPosition = new ConcurrentHashMap<>();

    public static boolean isAfk(ServerPlayer player) {
        Long last = lastActivity.get(player);
        if (last == null) {
            lastActivity.put(player, System.currentTimeMillis());
            lastPosition.put(player, player.position());
            return false;
        }
        Vec3 currentPos = player.position();
        Vec3 oldPos = lastPosition.get(player);
        if (oldPos != null && !oldPos.equals(currentPos)) {
            lastActivity.put(player, System.currentTimeMillis());
            lastPosition.put(player, currentPos);
            return false;
        }
        return System.currentTimeMillis() - last > AFK_TIMEOUT;
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
