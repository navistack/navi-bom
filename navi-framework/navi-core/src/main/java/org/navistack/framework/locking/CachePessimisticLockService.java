package org.navistack.framework.locking;

import org.navistack.framework.cache.CacheKeyBuilder;
import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.PrefixedCacheKeyBuilder;

import java.time.Duration;

public class CachePessimisticLockService implements PessimisticLockService {
    private final CacheKeyBuilder keyBuilder = new PrefixedCacheKeyBuilder(".", "NAVI", "P_LOCK");
    private final CacheService cacheService;

    public CachePessimisticLockService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public boolean tryLock(String userKey, Duration timeout) {
        String key = keyBuilder.build(userKey);
        return cacheService.setIfAbsent(key, "1", timeout);
    }

    @Override
    public boolean unlock(String userKey) {
        String key = keyBuilder.build(userKey);
        return cacheService.delete(key);
    }
}
