package org.navistack.framework.locking;

import org.navistack.framework.cache.CacheScope;
import org.navistack.framework.cache.CacheStore;
import org.navistack.framework.cache.HierarchicalCacheStoreBuilder;

import java.time.Duration;

public class CachePessimisticLockService implements PessimisticLockService {
    private final CacheStore cacheStore;

    public CachePessimisticLockService(HierarchicalCacheStoreBuilder cacheStoreBuilder) {
        this.cacheStore = cacheStoreBuilder.build(CacheScope.of("NAVI").scope("P_LOCK"));
    }

    @Override
    public boolean tryLock(String key, Duration timeout) {
        return cacheStore.setIfAbsent(key, "1", timeout);
    }

    @Override
    public boolean unlock(String key) {
        return cacheStore.delete(key);
    }
}
