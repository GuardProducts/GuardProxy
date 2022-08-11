package com.guardproducts.api.data;

import com.google.inject.Singleton;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class WhitelistManager {

    private final Set<String> ips = new HashSet<>();


    public boolean isWhitelisted(final String ip) {
        return ips.contains(ip);
    }

    public boolean addWhitelist(final String ip) {
        return ips.add(ip);
    }

    public boolean removeWhitelist(final String ip) {
        return ips.remove(ip);
    }

}
