package com.skd.infotab;

import com.skd.infotab.network.ActivityPingPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(InfoTab.MODID)
public class InfoTab {

    public static final String MODID = "infotab";

    private static final PlayerListHandler HANDLER = new PlayerListHandlerNeoForge();

    public InfoTab(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(this::registerPackets);
    }

    private void registerPackets(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");
        registrar.playToServer(ActivityPingPacket.TYPE, ActivityPingPacket.STREAM_CODEC, (payload, context) -> {
            context.enqueueWork(() -> {
                if (context.player() instanceof ServerPlayer sp) {
                    AfkTracker.updateActivity(sp);
                }
            });
        });
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

            boolean afk = false;
            if (Config.SHOW_AFK.get() && player instanceof ServerPlayer sp) {
                afk = AfkTracker.isAfk(sp);
            }

            MutableComponent newName = Component.literal("");

            if (afk) {
                MutableComponent afkTag = Component.literal("[AFK] ");
                Style creme = Style.EMPTY.withColor(
                        net.minecraft.network.chat.TextColor.parseColor("#FFFDD0").result().orElse(
                                net.minecraft.network.chat.TextColor.fromLegacyFormat(ChatFormatting.WHITE)));
                newName.append(afkTag.withStyle(creme));
            }

            if (Config.SHOW_DIMENSION.get()) {
                String color = Config.DEFAULT_COLOR.get();
                MutableComponent dimComponent = HANDLER.makeDimensionComponent(player, color);
                if (Config.DIM_POSITION.get() == CommonUtils.DimensionPosition.PREPEND) {
                    newName.append(dimComponent).append(" ").append(currentName);
                } else {
                    newName.append(currentName).append(" ").append(dimComponent);
                }
            } else {
                newName.append(currentName);
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
}
