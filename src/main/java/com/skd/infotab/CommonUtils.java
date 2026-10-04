package com.skd.infotab;

import net.minecraft.resources.ResourceLocation;

public class CommonUtils {

    public enum DimensionPosition {
        PREPEND,
        APPEND
    }

    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] parts = input.split("_");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) {
                result.append(part.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String splitIdentifier(ResourceLocation id, int index) {
        if (id == null) return "";
        String[] parts = {id.getNamespace(), id.getPath()};
        if (index >= 0 && index < parts.length) {
            return parts[index];
        }
        return "";
    }

    public static String splitIdentifier(String id, int index) {
        if (id == null || id.isEmpty()) return "";
        ResourceLocation parsed = ResourceLocation.tryParse(id);
        if (parsed == null) {
            String[] parts = id.split(":");
            if (index >= 0 && index < parts.length) {
                return parts[index];
            }
            return "";
        }
        return splitIdentifier(parsed, index);
    }

    public static String dimensionToString(ResourceLocation id) {
        if (id == null) return "";
        if (id.getNamespace().equals("minecraft")) {
            return toTitleCase(id.getPath());
        }
        return toTitleCase(id.getPath()) + " (" + id.getNamespace() + ")";
    }

    public static String dimensionToString(String id) {
        if (id == null || id.isEmpty()) return "";
        ResourceLocation parsed = ResourceLocation.tryParse(id);
        if (parsed != null) {
            return dimensionToString(parsed);
        }
        return toTitleCase(id);
    }

    public static int rgbToInt(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static int hexToInt(String hex) {
        if (hex == null || hex.isEmpty()) return 0xFFFFFF;
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        try {
            return (int) Long.parseLong(hex, 16);
        } catch (NumberFormatException e) {
            return 0xFFFFFF;
        }
    }

    public static int customColorToInt(String[] parts) {
        if (parts == null || parts.length < 3) return 0xFFFFFF;
        try {
            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            return rgbToInt(r, g, b);
        } catch (NumberFormatException e) {
            return 0xFFFFFF;
        }
    }
}
