package me.nerdoron.oscar.modules.jinx;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.jodah.expiringmap.ExpiringMap;

public class MessageCache {
    Map<String, String> cache = ExpiringMap.builder().expiration(2, TimeUnit.SECONDS)
            .build();

    public String getUser(String messageContent) {
        if (cache.containsKey(messageContent)) {
            return cache.get(messageContent);
        } else {
            return "";
        }
    }

    public void put(String messageContent, String user) {
        cache.put(messageContent, user);
    }
}