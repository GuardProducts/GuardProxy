package com.guardproducts.bukkit.initializers;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.guardproducts.api.initializers.Initializer;
import com.guardproducts.bukkit.listeners.PreLoginListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitListenerInitializer implements Initializer {

    @Inject
    private JavaPlugin plugin;
    @Inject
    private PluginManager pluginManager;


    @Override
    public void init(Injector injector) {
        pluginManager.registerEvents(injector.getInstance(PreLoginListener.class), plugin);
    }
}
