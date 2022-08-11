package com.guardproducts.bukkit.modules;

import com.google.inject.AbstractModule;
import com.guardproducts.bukkit.BukkitPlugin;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitModule extends AbstractModule {

    private final BukkitPlugin bukkitPlugin;

    public BukkitModule(BukkitPlugin bukkitPlugin) {
        this.bukkitPlugin = bukkitPlugin;
    }

    @Override
    protected void configure() {
        bind(JavaPlugin.class).toInstance(this.bukkitPlugin);
        bind(PluginManager.class).toInstance(this.bukkitPlugin.getServer().getPluginManager());
        bind(HttpClient.class).toInstance(HttpClientBuilder.create().build());
    }
}
