package org.navistack.framework.locking;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;

import java.time.Duration;

public class CachePessimisticLockService implements PessimisticLockService {
    private final CacheService cacheService;

    public CachePessimisticLockService(ScopedCacheServiceBuilder cacheServiceBuilder) {
        this.cacheService = cacheServiceBuilder.build("NAVI", "P_LOCK");
    }

    @Override
    public boolean tryLock(String key, Duration timeout) {
        return cacheService.setIfAbsent(key, "1", timeout);
    }

    @Override
    public boolean unlock(String key) {
        return cacheService.delete(key);
    }
}
