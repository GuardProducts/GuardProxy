package com.guardproducts.api.data;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Singleton;

import java.util.concurrent.TimeUnit;

@Singleton
public class CacheManager {

    private final Cache<String, Boolean> cachedAddresses = CacheBuilder.newBuilder().expireAfterWrite(6, TimeUnit.HOURS).build();

    public boolean isCached(final String address) {
        return cachedAddresses.getIfPresent(address) != null;
    }

    public boolean isDisallowedInCache(final String address) {
        final Boolean allowed = cachedAddresses.getIfPresent(address);
        return allowed != null && !allowed;
    }


    public void addToCache(final String address, final boolean b) {
        cachedAddresses.put(address, b);
    }




}
