package com.skd.dimensiontab;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.slf4j.Logger;

@Mod(DimensionTAB.MODID)
public class DimensionTAB {

    public static final String MODID = "dimensiontab";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final PlayerListHandler HANDLER = new PlayerListHandlerNeoForge();

    public DimensionTAB(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @EventBusSubscriber(modid = MODID)
    public static class ModEventHandler {

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event) {
            CustomCommands.registerCommands(event.getDispatcher());
        }

        @SubscribeEvent
        public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event) {
            PlayerListHandler.playerList.add(event.getEntity());
            refreshPlayer(event);
        }

        @SubscribeEvent
        public static void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
            PlayerListHandler.playerList.remove(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
            refreshPlayer(event);
        }

        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            refreshPlayer(event);
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void changeTabListName(PlayerEvent.TabListNameFormat event) {
            var player = event.getEntity();
            Component currentName = event.getDisplayName();
            if (currentName == null) {
                currentName = player.getName();
            }
            String color = Config.DEFAULT_COLOR.get();
            MutableComponent dimComponent = HANDLER.makeDimensionComponent(player, color);

            MutableComponent newName;
            if (Config.DIM_POSITION.get() == CommonUtils.DimensionPosition.PREPEND) {
                newName = Component.literal("").append(dimComponent).append(" ").append(currentName);
            } else {
                newName = Component.literal("").append(currentName).append(" ").append(dimComponent);
            }
            event.setDisplayName(newName);
        }

        private static void refreshPlayer(PlayerEvent event) {
            event.getEntity().refreshDisplayName();
            if (event.getEntity() instanceof ServerPlayer sp) {
                sp.refreshTabListName();
            }
        }
    }

    @EventBusSubscriber(modid = MODID)
    public static class ModEventBusEvents {

        @SubscribeEvent
        public static void onConfigReloading(ModConfigEvent.Reloading event) {
            if (event.getConfig().getType() == ModConfig.Type.COMMON && event.getConfig().getModId().equals(MODID)) {
                LOGGER.info("Dimension TAB config reloaded");
            }
        }
    }
}
