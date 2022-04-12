package com.wsy.exam.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @className: com.wsy.exam.utils-> RedisCache
 * @description: Redis工具类
 * @author: wsy
 * @createDate: 2022-04-05 18:24
 * @version: 1.0
 * @todo:
 */
@Component
@SuppressWarnings(value = {"uncheck", "rawtypes"})
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;


    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public boolean deleteCacheObject(final String key) {
        return redisTemplate.delete(key);
    }

    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    public <T> long setCacheList(final String key, final List<T> dataList) {
        return redisTemplate.opsForList().rightPushAll(key, dataList);
    }

    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperations.add(it.next());
        }
        return setOperations;
    }

    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public <T> void setCacheMapValue(final String key, final String hkey, final T value) {
        redisTemplate.opsForHash().put(key, hkey, value);
    }

    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    public <T> T getCacheMapValue(final String key, final String hkey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hkey);
    }

    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hkeys) {
        return redisTemplate.opsForHash().multiGet(key, hkeys);
    }

    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

}
