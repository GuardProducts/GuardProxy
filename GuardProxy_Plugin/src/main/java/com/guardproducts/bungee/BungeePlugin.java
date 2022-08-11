package com.guardproducts.bungee;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.guardproducts.bukkit.modules.BukkitModule;
import com.guardproducts.bungee.initializers.BungeeComandInitializer;
import com.guardproducts.bungee.initializers.BungeeListenerInitializer;
import com.guardproducts.bungee.modules.BungeeModule;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class BungeePlugin extends Plugin {

    private Configuration configuration;

    @Inject
    private BungeeListenerInitializer listenerInitializer;
    @Inject
    private BungeeComandInitializer bungeeComandInitializer;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        final Injector injector = Guice.createInjector(new BungeeModule(this, this.configuration));
        injector.injectMembers(this);

        listenerInitializer.init(injector);
        bungeeComandInitializer.init(injector);
    }

    private void saveDefaultConfig(){
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        final File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
