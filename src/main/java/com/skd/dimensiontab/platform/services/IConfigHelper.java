package com.skd.dimensiontab.platform.services;

import com.skd.dimensiontab.CommonUtils.DimensionPosition;
import net.minecraft.server.players.PlayerList;

import java.util.List;

public interface IConfigHelper {

    String BaseDefaultColor();
    String BaseOverworldColor();
    String BaseNetherColor();
    String BaseEndColor();
    String BaseListFormat();

    String DefaultColor();
    String OverworldColor();
    String NetherColor();
    String EndColor();
    String ListFormat();

    List<String> GetAllCustomColors();
    void AddCustomColor(String dimension, String color);
    boolean RemoveCustomColor(String dimension);

    String GetAlias(String dimension);
    String GetCustomColor(String dimension);
    void SetAlias(String dimension, String alias);
    void SetColor(String dimension, String color);

    void SetBoolSetting(String setting, boolean value);
    void ResetAlias(String dimension);
    void ResetColor(String dimension);
    void SetFormat(String format);
    void SetPlacement(DimensionPosition position);
    void RefreshPlayerData(PlayerList playerList);
    boolean HasAlias(String dimension);
}
