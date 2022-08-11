package com.guardproducts.bungee.initializers;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.guardproducts.api.initializers.Initializer;
import com.guardproducts.bungee.BungeePlugin;
import com.guardproducts.bungee.commands.WhitelistCommand;

public class BungeeComandInitializer implements Initializer {

    @Inject
    private BungeePlugin plugin;


    @Override
    public void init(Injector injector) {
        this.plugin.getProxy().getPluginManager().registerCommand(this.plugin, new WhitelistCommand("gpwhitelist"));
    }
}
