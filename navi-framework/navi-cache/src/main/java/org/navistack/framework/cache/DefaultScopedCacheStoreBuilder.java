package org.navistack.framework.cache;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class DefaultScopedCacheStoreBuilder implements ScopedCacheStoreBuilder {

    @Getter
    @Setter
    private CacheStore cacheStore;

    @Override
    public ScopedCacheStore build(CacheScope cacheScope) {
        if (cacheScope == null) {
            throw new IllegalStateException("cacheScope must not be null");
        }

        if (cacheStore == null) {
            throw new IllegalStateException("cacheStore must not be null");
        }

        return new ScopedCacheStore(cacheStore, cacheScope);
    }

    @Override
    public ScopedCacheStore build(@NonNull String scope) {
        return build(CacheScope.of(scope));
    }
}
