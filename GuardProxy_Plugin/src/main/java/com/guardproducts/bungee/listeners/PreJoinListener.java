package com.guardproducts.bungee.listeners;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.guardproducts.api.data.CacheManager;
import com.guardproducts.api.data.WhitelistManager;
import com.guardproducts.bungee.BungeePlugin;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PreJoinListener implements Listener {

    @Inject
    private HttpClient client;
    @Inject
    private Logger logger;
    @Inject
    private CacheManager cacheManager;
    @Inject
    private WhitelistManager whitelistManager;
    @Inject
    private Plugin plugin;

    private String kickMessage;
    private String licenseKey;

    @Inject
    public PreJoinListener(@Named("kickMessage") String kickMessage, @Named("licenseKey") String licenseKey) {
        this.kickMessage = kickMessage;
        this.licenseKey = licenseKey;
    }



    @EventHandler
    public void onJoin(final PreLoginEvent e) {
        if(e.isCancelled()) {
            return;
        }
        final String ip = String.valueOf(e.getConnection().getAddress().getAddress());
        if(whitelistManager.isWhitelisted(ip)) {
            return;
        }
        if(cacheManager.isCached(ip) && cacheManager.isDisallowedInCache(ip)) {
            e.setCancelReason(this.kickMessage);
            e.setCancelled(true);
            return;
        }
        try {
            e.registerIntent(plugin);
            HttpGet request = new HttpGet("http://api.mcguard.pl:8080/check?ip=" + ip + "&license=" + this.licenseKey);
            final HttpResponse httpResponse = client.execute(request);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                HttpEntity entity = httpResponse.getEntity();
                final String content = EntityUtils.toString(entity);
                if(content == null) {
                    logger.log(Level.INFO, "API returned empty response for IP: " + ip);
                    e.completeIntent(this.plugin);
                    return;
                }
                boolean isProxy = content.equalsIgnoreCase("true");
                cacheManager.addToCache(ip, isProxy);
                if(isProxy) {
                    e.setCancelReason(this.kickMessage);
                    e.setCancelled(true);
                    e.completeIntent(this.plugin);
                    return;
                }
                e.completeIntent(this.plugin);
            } else {
                logger.log(Level.INFO, "API returned status code - " + statusCode + " can't check IP address!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
