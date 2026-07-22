package com.skd.dimensiontab;

import com.skd.dimensiontab.platform.Services;

public class PlayerListHandlerNeoForge extends PlayerListHandler {

    @Override
    public String checkForAliases(String dimension) {
        if (Config.ENABLE_ALIASES.get()) {
            String alias = Services.CONFIG.GetAlias(dimension);
            if (alias != null && !alias.isEmpty()) {
                return alias;
            }
        }
        return null;
    }
}
