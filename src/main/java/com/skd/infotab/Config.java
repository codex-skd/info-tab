package com.skd.infotab;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final String MODID_REGEX = "^[a-z0-9_]+:[a-z0-9_/]+$";
    public static final String DEFAULT_LIST_FORMAT = "[%dim:name%]";
    public static final String BASE_DEFAULT_COLOR = "gold";
    public static final String BASE_OVERWORLD_COLOR = "green";
    public static final String BASE_NETHER_COLOR = "red";
    public static final String BASE_END_COLOR = "dark_purple";

    public static ModConfigSpec.ConfigValue<String> LIST_FORMAT;
    public static ModConfigSpec.EnumValue<CommonUtils.DimensionPosition> DIM_POSITION;
    public static ModConfigSpec.ConfigValue<String> DEFAULT_COLOR;
    public static ModConfigSpec.ConfigValue<String> OVERWORLD_COLOR;
    public static ModConfigSpec.ConfigValue<String> NETHER_COLOR;
    public static ModConfigSpec.ConfigValue<String> END_COLOR;
    public static ModConfigSpec.BooleanValue PER_DIM_COLOR;
    public static ModConfigSpec.BooleanValue ENABLE_ALIASES;
    public static ModConfigSpec.ConfigValue<List<? extends String>> MODDED_DIMS;
    public static ModConfigSpec.ConfigValue<List<? extends String>> DIM_ALIASES;
    public static ModConfigSpec.ConfigValue<List<? extends String>> CUSTOM_COLORS;

    public static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Info TAB Configuration").push("general");

        builder.comment("General customization options").push("customization");
        LIST_FORMAT = builder
                .comment("Format for displaying the dimension. Available tokens: %dim:id%, %dim:name%, %dim:namespace%, %dim:path%")
                .define("listFormat", DEFAULT_LIST_FORMAT);
        DIM_POSITION = builder
                .comment("Where to place the dimension tag relative to the player name")
                .defineEnum("dimPosition", CommonUtils.DimensionPosition.APPEND);
        ENABLE_ALIASES = builder
                .comment("Enable dimension aliases (custom names for dimensions)")
                .define("enableAliases", true);
        builder.pop();

        builder.comment("Per-dimension color customization").push("dimensionColors");
        PER_DIM_COLOR = builder
                .comment("Use different colors for different dimensions")
                .define("perDimColor", true);
        DEFAULT_COLOR = builder
                .comment("Default color for dimensions without a specific color. Can be Minecraft color names (gold, red, etc.) or hex (#FF0000)")
                .define("defaultColor", BASE_DEFAULT_COLOR);
        OVERWORLD_COLOR = builder
                .comment("Color for the Overworld dimension")
                .define("overworldColor", BASE_OVERWORLD_COLOR);
        NETHER_COLOR = builder
                .comment("Color for the Nether dimension")
                .define("netherColor", BASE_NETHER_COLOR);
        END_COLOR = builder
                .comment("Color for the End dimension")
                .define("endColor", BASE_END_COLOR);
        builder.pop();

        builder.comment("Customization for modded dimensions").push("moddedDimensions");
        MODDED_DIMS = builder
                .comment("List of modded dimension IDs to track (e.g., 'aether:the_aether')")
                .defineListAllowEmpty("moddedDimensions", new ArrayList<String>(), () -> "", Config::validateDimensionId);
        DIM_ALIASES = builder
                .comment("Dimension aliases in the format 'dimension_id:alias'. Example: 'aether:the_aether:Aether'")
                .defineListAllowEmpty("dimAliases", new ArrayList<String>(), () -> "", Config::validateAliasFormat);
        builder.pop();

        builder.comment("Extra customization options").push("extra");
        CUSTOM_COLORS = builder
                .comment("Custom colors for specific dimensions. Format: 'dimension_id:hex_or_name'. Example: 'aether:the_aether:#00FFFF'")
                .defineListAllowEmpty("customColors", new ArrayList<String>(), () -> "", Config::validateColorFormat);
        builder.pop();

        builder.pop();
        SPEC = builder.build();
    }

    private static boolean validateDimensionId(Object obj) {
        return obj instanceof String s && s.matches("[a-z0-9_]+:[a-z0-9_/*]+");
    }

    private static boolean validateAliasFormat(Object obj) {
        return obj instanceof String s && s.split(":").length >= 3;
    }

    private static boolean validateColorFormat(Object obj) {
        return obj instanceof String s && s.split(":").length >= 3;
    }
}
