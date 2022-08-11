package com.guardproducts.bungee.initializers;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.guardproducts.api.initializers.Initializer;
import com.guardproducts.bungee.listeners.PreJoinListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeListenerInitializer implements Initializer {

    @Inject
    private Plugin plugin;

    @Inject
    private PluginManager pluginManager;


    @Override
    public void init(Injector injector) {
        pluginManager.registerListener(this.plugin, injector.getInstance(PreJoinListener.class));
    }
}
