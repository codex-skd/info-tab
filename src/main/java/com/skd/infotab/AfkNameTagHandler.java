package com.skd.infotab;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;

@EventBusSubscriber(modid = InfoTab.MODID, value = Dist.CLIENT)
public class AfkNameTagHandler {

    private static final String AFK_PREFIX = "[AFK] ";

    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent.CanRender event) {
        if (!Config.SHOW_AFK.get()) return;
        if (!(event.getEntity() instanceof Player player)) return;

        var connection = Minecraft.getInstance().getConnection();
        if (connection == null) return;

        PlayerInfo info = connection.getPlayerInfo(player.getUUID());
        if (info == null) return;

        Component tabName = info.getTabListDisplayName();
        if (tabName == null || !tabName.getString().startsWith(AFK_PREFIX)) return;

        Component content = event.getContent();
        if (content == null) content = player.getDisplayName();
        if (content == null || content.getString().startsWith(AFK_PREFIX)) return;

        Style creme = Style.EMPTY.withColor(
                TextColor.parseColor("#FFFDD0").result().orElse(
                        TextColor.fromLegacyFormat(ChatFormatting.WHITE)));

        MutableComponent newContent = Component.literal(AFK_PREFIX).withStyle(creme)
                .append(content);
        event.setContent(newContent);
    }
}
