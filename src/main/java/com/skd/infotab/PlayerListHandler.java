package com.skd.infotab;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerListHandler {

    public static final List<Player> playerList = new ArrayList<>();

    public MutableComponent makeDimensionComponent(Player player, String color) {
        Identifier dimension = player.level().dimension().identifier();
        String colorStr = resolveDimensionColor(dimension, color);
        MutableComponent component = extractTokensFromFormat(player, dimension);
        Style style = Style.EMPTY.withColor(parseColor(colorStr));

        return component.withStyle(style);
    }

    private String resolveDimensionColor(Identifier dimension, String color) {
        if (!Config.PER_DIM_COLOR.get()) {
            return color;
        }

        String customColor = com.skd.infotab.platform.Services.CONFIG.GetCustomColor(dimension.toString());
        if (customColor != null) {
            return customColor;
        }

        return switch (dimension.toString()) {
            case "minecraft:overworld" -> com.skd.infotab.platform.Services.CONFIG.OverworldColor();
            case "minecraft:the_nether" -> com.skd.infotab.platform.Services.CONFIG.NetherColor();
            case "minecraft:the_end" -> com.skd.infotab.platform.Services.CONFIG.EndColor();
            default -> color;
        };
    }

    private MutableComponent extractTokensFromFormat(Player player, Identifier dimension) {
        String format = com.skd.infotab.platform.Services.CONFIG.ListFormat();
        MutableComponent result = Component.literal("");
        StringBuilder currentText = new StringBuilder();
        boolean inToken = false;
        StringBuilder tokenBuffer = new StringBuilder();

        for (int i = 0; i < format.length(); i++) {
            char c = format.charAt(i);
            if (c == '%') {
                if (inToken) {
                    String token = tokenBuffer.toString();
                    String[] parts = token.split(":");
                    if (parts.length >= 2 && parts[0].equals("dim")) {
                        String tokenValue = resolveTokenValue(parts[1], dimension);
                        if (!currentText.isEmpty()) {
                            result.append(Component.literal(currentText.toString()));
                            currentText.setLength(0);
                        }
                        result.append(Component.literal(tokenValue));
                    } else {
                        currentText.append('%').append(token).append('%');
                    }
                    tokenBuffer.setLength(0);
                    inToken = false;
                } else {
                    if (!currentText.isEmpty()) {
                        result.append(Component.literal(currentText.toString()));
                        currentText.setLength(0);
                    }
                    inToken = true;
                }
            } else if (inToken) {
                tokenBuffer.append(c);
            } else {
                currentText.append(c);
            }
        }

        if (!currentText.isEmpty()) {
            result.append(Component.literal(currentText.toString()));
        }
        if (inToken) {
            result.append(Component.literal("%" + tokenBuffer.toString()));
        }

        return result;
    }

    private String resolveTokenValue(String tokenType, Identifier dimension) {
        return switch (tokenType) {
            case "id" -> dimension.toString();
            case "name" -> {
                String alias = checkForAliases(dimension.toString());
                yield alias != null ? alias : CommonUtils.dimensionToString(dimension);
            }
            case "namespace" -> dimension.getNamespace();
            case "path" -> dimension.getPath();
            default -> dimension.getPath();
        };
    }

    public abstract String checkForAliases(String dimension);

    public static net.minecraft.network.chat.TextColor parseColor(String colorStr) {
        net.minecraft.network.chat.TextColor fallback = net.minecraft.network.chat.TextColor.fromLegacyFormat(ChatFormatting.GOLD);
        if (colorStr == null || colorStr.isEmpty()) {
            return fallback;
        }

        String normalized = colorStr.toLowerCase();
        return net.minecraft.network.chat.TextColor.parseColor(normalized).result()
                .or(() -> net.minecraft.network.chat.TextColor.parseColor("#" + normalized).result())
                .orElse(fallback);
    }
}
