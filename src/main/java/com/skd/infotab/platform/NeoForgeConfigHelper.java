package com.skd.infotab.platform;

import com.skd.infotab.CommonUtils;
import com.skd.infotab.Config;
import com.skd.infotab.PlayerListHandler;
import com.skd.infotab.platform.services.IConfigHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import java.util.ArrayList;
import java.util.List;

public class NeoForgeConfigHelper implements IConfigHelper {

    private static final String ALIAS_SEPARATOR = ":";

    @Override
    public String BaseDefaultColor() {
        return Config.BASE_DEFAULT_COLOR;
    }

    @Override
    public String BaseOverworldColor() {
        return Config.BASE_OVERWORLD_COLOR;
    }

    @Override
    public String BaseNetherColor() {
        return Config.BASE_NETHER_COLOR;
    }

    @Override
    public String BaseEndColor() {
        return Config.BASE_END_COLOR;
    }

    @Override
    public String BaseListFormat() {
        return Config.DEFAULT_LIST_FORMAT;
    }

    @Override
    public String DefaultColor() {
        return Config.DEFAULT_COLOR.get();
    }

    @Override
    public String OverworldColor() {
        return Config.OVERWORLD_COLOR.get();
    }

    @Override
    public String NetherColor() {
        return Config.NETHER_COLOR.get();
    }

    @Override
    public String EndColor() {
        return Config.END_COLOR.get();
    }

    @Override
    public String ListFormat() {
        return Config.LIST_FORMAT.get();
    }

    @Override
    public List<String> GetAllCustomColors() {
        return new ArrayList<>(Config.CUSTOM_COLORS.get());
    }

    @Override
    public void AddCustomColor(String dimension, String color) {
        String entry = dimension + ALIAS_SEPARATOR + color;
        List<String> list = new ArrayList<>(Config.CUSTOM_COLORS.get());
        list.removeIf(s -> s.startsWith(dimension + ALIAS_SEPARATOR));
        list.add(entry);
        Config.CUSTOM_COLORS.set(list);
    }

    @Override
    public boolean RemoveCustomColor(String dimension) {
        List<String> list = new ArrayList<>(Config.CUSTOM_COLORS.get());
        boolean removed = list.removeIf(s -> s.startsWith(dimension + ALIAS_SEPARATOR));
        Config.CUSTOM_COLORS.set(list);
        return removed;
    }

    @Override
    public String GetAlias(String dimension) {
        if (!Config.ENABLE_ALIASES.get()) return null;
        String prefix = dimension + ALIAS_SEPARATOR;
        for (String entry : Config.DIM_ALIASES.get()) {
            if (entry.startsWith(prefix)) {
                String[] parts = entry.split(ALIAS_SEPARATOR, 3);
                if (parts.length >= 3) {
                    return parts[2];
                }
            }
        }
        return null;
    }

    @Override
    public String GetCustomColor(String dimension) {
        String prefix = dimension + ALIAS_SEPARATOR;
        for (String entry : Config.CUSTOM_COLORS.get()) {
            if (entry.startsWith(prefix)) {
                String[] parts = entry.split(ALIAS_SEPARATOR, 3);
                if (parts.length >= 3) {
                    return parts[2];
                }
            }
        }
        return null;
    }

    @Override
    public void SetAlias(String dimension, String alias) {
        String entry = dimension + ALIAS_SEPARATOR + alias;
        List<String> list = new ArrayList<>(Config.DIM_ALIASES.get());
        list.removeIf(s -> s.startsWith(dimension + ALIAS_SEPARATOR));
        list.add(entry);
        Config.DIM_ALIASES.set(list);
    }

    @Override
    public void SetColor(String dimension, String color) {
        AddCustomColor(dimension, color);
    }

    @Override
    public void SetBoolSetting(String setting, boolean value) {
        switch (setting) {
            case "perDimColor":
                Config.PER_DIM_COLOR.set(value);
                break;
            case "enableAliases":
                Config.ENABLE_ALIASES.set(value);
                break;
            case "showAfk":
                Config.SHOW_AFK.set(value);
                break;
            case "showDimension":
                Config.SHOW_DIMENSION.set(value);
                break;
        }
    }

    @Override
    public void ResetAlias(String dimension) {
        List<String> list = new ArrayList<>(Config.DIM_ALIASES.get());
        list.removeIf(s -> s.startsWith(dimension + ALIAS_SEPARATOR));
        Config.DIM_ALIASES.set(list);
    }

    @Override
    public void ResetColor(String dimension) {
        RemoveCustomColor(dimension);
    }

    @Override
    public void SetFormat(String format) {
        Config.LIST_FORMAT.set(format);
    }

    @Override
    public void SetPlacement(CommonUtils.DimensionPosition position) {
        Config.DIM_POSITION.set(position);
    }

    @Override
    public void RefreshPlayerData(PlayerList playerList) {
        for (var entry : PlayerListHandler.playerList) {
            entry.refreshDisplayName();
            if (entry instanceof ServerPlayer sp) {
                sp.refreshTabListName();
            }
        }
    }

    @Override
    public boolean HasAlias(String dimension) {
        String prefix = dimension + ALIAS_SEPARATOR;
        for (String entry : Config.DIM_ALIASES.get()) {
            if (entry.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
