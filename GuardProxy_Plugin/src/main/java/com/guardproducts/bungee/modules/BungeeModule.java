package com.guardproducts.bungee.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.guardproducts.bungee.BungeePlugin;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class BungeeModule extends AbstractModule {

    private final BungeePlugin plugin;
    private final Configuration configuration;

    public BungeeModule(BungeePlugin plugin, Configuration configuration) {
        this.plugin = plugin;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(this.plugin);
        bind(PluginManager.class).toInstance(this.plugin.getProxy().getPluginManager());
        bind(Configuration.class).toInstance(this.configuration);
        bind(String.class).annotatedWith(Names.named("kickMessage")).toInstance(this.configuration.getString("kick-message"));
        bind(String.class).annotatedWith(Names.named("licenseKey")).toInstance(this.configuration.getString("license-key"));
        bind(String.class).annotatedWith(Names.named("wlAddedMessage")).toInstance(this.configuration.getString("wladded-message"));
        bind(String.class).annotatedWith(Names.named("wlRemovedMessage")).toInstance(this.configuration.getString("wlremoved-message"));
        bind(String.class).annotatedWith(Names.named("wlExistsMessage")).toInstance(this.configuration.getString("wlexists-message"));
        bind(String.class).annotatedWith(Names.named("wlNoExistsMessage")).toInstance(this.configuration.getString("wlnoexists-message"));
        bind(HttpClient.class).toInstance(HttpClientBuilder.create().build());
    }
}

