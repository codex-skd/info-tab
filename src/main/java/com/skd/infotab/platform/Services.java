package com.skd.infotab.platform;

import com.skd.infotab.platform.services.IConfigHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IConfigHelper CONFIG = load(IConfigHelper.class);

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Cannot load service: " + clazz.getName()));
    }
}
