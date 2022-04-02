package me.nerdoron.oscar.modules.jinx;

import java.util.HashMap;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JinxChannelCache extends ListenerAdapter {

    // key: channel ; value: hashmap cache
    private static HashMap<String, MessageCache> channelCache = new HashMap<String, MessageCache>();

    public static MessageCache getCache(String channelId) {
        if (channelCache.containsKey(channelId)) {
            return channelCache.get(channelId);
        } else {
            MessageCache cache = new MessageCache();
            channelCache.put(channelId, cache);
            return cache;
        }

    }
}
