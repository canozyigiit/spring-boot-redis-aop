package com.redisaop.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> template;

    @Value("${spring.redis.cache.enabled:true}")
    private boolean cacheEnabled;


    public void clearAllCaches() {
        template.execute((RedisCallback<Object>) connection -> {
            connection.flushAll();
            return null;
        });
    }



    public void cachePut(String key, Object toBeCached, long ttlMinutes) {
        if (!cacheEnabled)
            return;

        template.opsForValue().set(key, toBeCached, ttlMinutes, TimeUnit.MINUTES);
    }


    public void cachePut(String key, Object toBeCached) {
        if (!cacheEnabled)
            return;

        if (toBeCached == null)
            return;

        cachePut(key, toBeCached, -1);
    }

    public <T> T cacheGet(String key, Class<T> type) {
        if (!cacheEnabled)
            return null;

        return (T) template.opsForValue().get(key);

    }
}