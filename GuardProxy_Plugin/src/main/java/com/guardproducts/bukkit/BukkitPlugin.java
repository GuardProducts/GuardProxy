package com.guardproducts.bukkit;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.guardproducts.bukkit.initializers.BukkitListenerInitializer;
import com.guardproducts.bukkit.modules.BukkitModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BukkitPlugin extends JavaPlugin {

    @Inject
    private BukkitListenerInitializer listenerInitializer;
    @Inject
    private Logger logger;

    @Override
    public void onEnable() {
       /* final Injector injector = Guice.createInjector(new BukkitModule(this));
        injector.injectMembers(this);

        listenerInitializer.init(injector); */
        logger.log(Level.INFO, "Plugin is not supported for bukkit for now! //TODO");
    }

}
