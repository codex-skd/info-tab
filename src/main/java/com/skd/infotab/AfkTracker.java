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
    private static final Map<ServerPlayer, Boolean> afkStatus = new ConcurrentHashMap<>();
    private static int tickCounter = 0;
    private static final org.slf4j.Logger LOGGER = com.mojang.logging.LogUtils.getLogger();

    public static boolean isAfk(ServerPlayer player) {
        Long last = lastActivity.get(player);
        if (last == null) {
            lastActivity.put(player, System.currentTimeMillis());
            lastPosition.put(player, player.position());
            LOGGER.info("[AFK] Tracking started for {}", player.getName().getString());
            return false;
        }
        long timeout = Config.AFK_TIMEOUT_MINUTES.get() * 60_000L;
        long elapsed = System.currentTimeMillis() - last;
        boolean afk = elapsed > timeout;
        if (afk && !afkStatus.containsKey(player)) {
            afkStatus.put(player, true);
            LOGGER.info("[AFK] {} is now AFK ({} min inactive)", player.getName().getString(), Config.AFK_TIMEOUT_MINUTES.get());
        }
        if (!afk && afkStatus.containsKey(player)) {
            afkStatus.remove(player);
            LOGGER.info("[AFK] {} is no longer AFK (activity detected)", player.getName().getString());
        }
        return afk;
    }

    public static void updateActivity(ServerPlayer player) {
        lastActivity.put(player, System.currentTimeMillis());
        lastPosition.put(player, player.position());
        if (afkStatus.remove(player) != null) {
            LOGGER.info("[AFK] {} is back from AFK", player.getName().getString());
        }
    }

    public static void removePlayer(ServerPlayer player) {
        lastActivity.remove(player);
        lastPosition.remove(player);
        afkStatus.remove(player);
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        tickCounter++;
        // Every 5 seconds (100 ticks): track movement
        if (tickCounter % 100 == 0) {
            for (var player : event.getServer().getPlayerList().getPlayers()) {
                Vec3 currentPos = player.position();
                Vec3 oldPos = lastPosition.get(player);
                if (oldPos != null && !oldPos.equals(currentPos)) {
                    lastActivity.put(player, System.currentTimeMillis());
                    lastPosition.put(player, currentPos);
                    if (afkStatus.remove(player) != null) {
                        LOGGER.info("[AFK] {} moved, activity reset", player.getName().getString());
                    }
                } else if (oldPos == null) {
                    lastPosition.put(player, currentPos);
                }
            }
        }
        // Every 30 seconds (600 ticks): log countdown for players approaching AFK
        if (tickCounter % 600 == 0) {
            int timeout = Config.AFK_TIMEOUT_MINUTES.get();
            for (var player : event.getServer().getPlayerList().getPlayers()) {
                Long last = lastActivity.get(player);
                if (last != null && !afkStatus.containsKey(player)) {
                    long elapsed = System.currentTimeMillis() - last;
                    int remaining = timeout - (int)(elapsed / 60_000);
                    if (remaining > 0 && remaining <= 5) {
                        LOGGER.info("[AFK] {} will be AFK in ~{} min", player.getName().getString(), remaining);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer sp) updateActivity(sp);
    }

    @SubscribeEvent
    public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity() instanceof ServerPlayer sp) updateActivity(sp);
    }

    @SubscribeEvent
    public static void onPlayerRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        if (event.getEntity() instanceof ServerPlayer sp) updateActivity(sp);
    }

    @SubscribeEvent
    public static void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer sp) updateActivity(sp);
    }

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntity() instanceof ServerPlayer sp) updateActivity(sp);
    }

    @SubscribeEvent
    public static void onPlayerChat(ServerChatEvent event) {
        if (event.getPlayer() != null) updateActivity(event.getPlayer());
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) removePlayer(sp);
    }
}
