package com.skd.infotab;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.skd.infotab.platform.Services;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Set;

public class CustomCommands {

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        var builder = Commands.literal("infotab")
                .requires(src -> src.getEntity() instanceof ServerPlayer)
                .executes(ctx -> showHelp(ctx));

        builder.then(Commands.literal("color")
                .then(Commands.argument("dimension", StringArgumentType.word())
                        .suggests((ctx, sb) -> SharedSuggestionProvider.suggest(getDimensionSuggestions(), sb))
                        .then(Commands.argument("color", StringArgumentType.greedyString())
                                .executes(ctx -> setColor(ctx, StringArgumentType.getString(ctx, "dimension"), StringArgumentType.getString(ctx, "color")))))
                .then(Commands.literal("default")
                        .then(Commands.argument("color", StringArgumentType.greedyString())
                                .executes(ctx -> setDefaultColor(ctx, StringArgumentType.getString(ctx, "color")))))
                .then(Commands.literal("overworld")
                        .then(Commands.argument("color", StringArgumentType.greedyString())
                                .executes(ctx -> setOverworldColor(ctx, StringArgumentType.getString(ctx, "color")))))
                .then(Commands.literal("nether")
                        .then(Commands.argument("color", StringArgumentType.greedyString())
                                .executes(ctx -> setNetherColor(ctx, StringArgumentType.getString(ctx, "color")))))
                .then(Commands.literal("end")
                        .then(Commands.argument("color", StringArgumentType.greedyString())
                                .executes(ctx -> setEndColor(ctx, StringArgumentType.getString(ctx, "color"))))));

        builder.then(Commands.literal("alias")
                .then(Commands.argument("dimension", StringArgumentType.word())
                        .suggests((ctx, sb) -> SharedSuggestionProvider.suggest(getDimensionSuggestions(), sb))
                        .then(Commands.argument("alias", StringArgumentType.greedyString())
                                .executes(ctx -> setAlias(ctx, StringArgumentType.getString(ctx, "dimension"), StringArgumentType.getString(ctx, "alias"))))));

        builder.then(Commands.literal("resetalias")
                .then(Commands.argument("dimension", StringArgumentType.word())
                        .suggests((ctx, sb) -> SharedSuggestionProvider.suggest(getAliasedDimensionSuggestions(), sb))
                        .executes(ctx -> resetAlias(ctx, StringArgumentType.getString(ctx, "dimension")))));

        builder.then(Commands.literal("resetcolor")
                .then(Commands.argument("dimension", StringArgumentType.word())
                        .suggests((ctx, sb) -> SharedSuggestionProvider.suggest(getColoredDimensionSuggestions(), sb))
                        .executes(ctx -> resetColor(ctx, StringArgumentType.getString(ctx, "dimension")))));

        builder.then(Commands.literal("format")
                .then(Commands.argument("format", StringArgumentType.greedyString())
                        .executes(ctx -> setFormat(ctx, StringArgumentType.getString(ctx, "format")))));

        builder.then(Commands.literal("placement")
                .then(Commands.literal("prepend")
                        .executes(ctx -> setPlacement(ctx, CommonUtils.DimensionPosition.PREPEND)))
                .then(Commands.literal("append")
                        .executes(ctx -> setPlacement(ctx, CommonUtils.DimensionPosition.APPEND))));

        builder.then(Commands.literal("setting")
                .then(Commands.argument("key", StringArgumentType.word())
                        .suggests((ctx, sb) -> SharedSuggestionProvider.suggest(
                                Set.of("perDimColor", "enableAliases", "showAfk", "showDimension"), sb))
                        .then(Commands.argument("value", BoolArgumentType.bool())
                                .executes(ctx -> setBoolSetting(ctx,
                                        StringArgumentType.getString(ctx, "key"),
                                        BoolArgumentType.getBool(ctx, "value"))))));

        builder.then(Commands.literal("dimid")
                .executes(ctx -> getDimensionId(ctx)));

        builder.then(Commands.literal("refresh")
                .executes(ctx -> refreshDisplayNames(ctx)));

        builder.then(Commands.literal("list")
                .then(Commands.literal("aliases")
                        .executes(ctx -> listAliases(ctx)))
                .then(Commands.literal("colors")
                        .executes(ctx -> listColors(ctx))));

        builder.then(Commands.literal("reload")
                .executes(ctx -> reloadConfig(ctx)));

        dispatcher.register(builder);
    }

    private static Set<String> getDimensionSuggestions() {
        Set<String> dims = new java.util.HashSet<>();
        dims.add("minecraft:overworld");
        dims.add("minecraft:the_nether");
        dims.add("minecraft:the_end");
        return dims;
    }

    private static Set<String> getAliasedDimensionSuggestions() {
        Set<String> dims = new java.util.HashSet<>();
        for (String entry : Config.DIM_ALIASES.get()) {
            String[] parts = entry.split(":", 3);
            if (parts.length >= 2) {
                dims.add(parts[0] + ":" + parts[1]);
            }
        }
        return dims;
    }

    private static Set<String> getColoredDimensionSuggestions() {
        Set<String> dims = new java.util.HashSet<>();
        for (String entry : Config.CUSTOM_COLORS.get()) {
            String[] parts = entry.split(":", 3);
            if (parts.length >= 2) {
                dims.add(parts[0] + ":" + parts[1]);
            }
        }
        return dims;
    }

    private static int showHelp(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack src = ctx.getSource();
        src.sendSuccess(() -> Component.literal("=== Info TAB Commands ==="), false);
        src.sendSuccess(() -> Component.literal("/infotab color <dimension> <color> - Set color for a dimension"), false);
        src.sendSuccess(() -> Component.literal("/infotab color default|overworld|nether|end <color> - Set preset dimension color"), false);
        src.sendSuccess(() -> Component.literal("/infotab alias <dimension> <alias> - Set an alias for a dimension"), false);
        src.sendSuccess(() -> Component.literal("/infotab resetalias <dimension> - Reset a dimension alias"), false);
        src.sendSuccess(() -> Component.literal("/infotab resetcolor <dimension> - Reset a custom dimension color"), false);
        src.sendSuccess(() -> Component.literal("/infotab format <format> - Set the display format (use %dim:name%, %dim:id%, etc.)"), false);
        src.sendSuccess(() -> Component.literal("/infotab placement prepend|append - Set dimension tag position"), false);
        src.sendSuccess(() -> Component.literal("/infotab setting <key> <true|false> - Toggle a boolean setting"), false);
        src.sendSuccess(() -> Component.literal("/infotab dimid - Show your current dimension ID"), false);
        src.sendSuccess(() -> Component.literal("/infotab refresh - Refresh all online players' display names"), false);
        src.sendSuccess(() -> Component.literal("/infotab list aliases|colors - List all aliases or custom colors"), false);
        src.sendSuccess(() -> Component.literal("/infotab reload - Reload config and refresh players"), false);
        return 1;
    }

    private static int setColor(CommandContext<CommandSourceStack> ctx, String dimension, String color) {
        Services.CONFIG.SetColor(dimension, color);
        ctx.getSource().sendSuccess(() -> Component.literal("Set color for " + dimension + " to " + color), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setDefaultColor(CommandContext<CommandSourceStack> ctx, String color) {
        Config.DEFAULT_COLOR.set(color);
        ctx.getSource().sendSuccess(() -> Component.literal("Set default color to " + color), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setOverworldColor(CommandContext<CommandSourceStack> ctx, String color) {
        Config.OVERWORLD_COLOR.set(color);
        ctx.getSource().sendSuccess(() -> Component.literal("Set overworld color to " + color), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setNetherColor(CommandContext<CommandSourceStack> ctx, String color) {
        Config.NETHER_COLOR.set(color);
        ctx.getSource().sendSuccess(() -> Component.literal("Set nether color to " + color), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setEndColor(CommandContext<CommandSourceStack> ctx, String color) {
        Config.END_COLOR.set(color);
        ctx.getSource().sendSuccess(() -> Component.literal("Set end color to " + color), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setAlias(CommandContext<CommandSourceStack> ctx, String dimension, String alias) {
        Services.CONFIG.SetAlias(dimension, alias);
        ctx.getSource().sendSuccess(() -> Component.literal("Set alias for " + dimension + " to " + alias), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int resetAlias(CommandContext<CommandSourceStack> ctx, String dimension) {
        Services.CONFIG.ResetAlias(dimension);
        ctx.getSource().sendSuccess(() -> Component.literal("Reset alias for " + dimension), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int resetColor(CommandContext<CommandSourceStack> ctx, String dimension) {
        Services.CONFIG.ResetColor(dimension);
        ctx.getSource().sendSuccess(() -> Component.literal("Reset custom color for " + dimension), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setFormat(CommandContext<CommandSourceStack> ctx, String format) {
        Services.CONFIG.SetFormat(format);
        ctx.getSource().sendSuccess(() -> Component.literal("Set display format to: " + format), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setPlacement(CommandContext<CommandSourceStack> ctx, CommonUtils.DimensionPosition position) {
        Services.CONFIG.SetPlacement(position);
        ctx.getSource().sendSuccess(() -> Component.literal("Set dimension position to " + position.name()), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int setBoolSetting(CommandContext<CommandSourceStack> ctx, String key, boolean value) {
        Services.CONFIG.SetBoolSetting(key, value);
        ctx.getSource().sendSuccess(() -> Component.literal("Set " + key + " to " + value), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }

    private static int getDimensionId(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getEntity() instanceof ServerPlayer player) {
            ResourceLocation dim = player.level().dimension().location();
            String dimName = CommonUtils.dimensionToString(dim);
            ctx.getSource().sendSuccess(() -> Component.literal("Current dimension: " + dim + " (" + dimName + ")"), false);
        }
        return 1;
    }

    private static int refreshDisplayNames(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        ctx.getSource().sendSuccess(() -> Component.literal("Refreshed display names for all online players."), true);
        return 1;
    }

    private static int listAliases(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack src = ctx.getSource();
        src.sendSuccess(() -> Component.literal("=== Dimension Aliases ==="), false);
        for (String entry : Config.DIM_ALIASES.get()) {
            String[] parts = entry.split(":", 3);
            if (parts.length >= 3) {
                src.sendSuccess(() -> Component.literal(parts[0] + ":" + parts[1] + " -> " + parts[2]), false);
            }
        }
        return 1;
    }

    private static int listColors(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack src = ctx.getSource();
        src.sendSuccess(() -> Component.literal("=== Custom Dimension Colors ==="), false);
        for (String entry : Config.CUSTOM_COLORS.get()) {
            String[] parts = entry.split(":", 3);
            if (parts.length >= 3) {
                src.sendSuccess(() -> Component.literal(parts[0] + ":" + parts[1] + " -> " + parts[2]), false);
            }
        }
        return 1;
    }

    private static int reloadConfig(CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().sendSuccess(() -> Component.literal("Configuration reloaded."), true);
        if (ctx.getSource().getServer() != null)
            Services.CONFIG.RefreshPlayerData(ctx.getSource().getServer().getPlayerList());
        return 1;
    }
}
