package org.navistack.framework.cache;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class DefaultHierarchicalCacheStoreBuilder implements HierarchicalCacheStoreBuilder {

    @Getter
    @Setter
    private CacheStore cacheStore;

    @Override
    public DefaultHierarchicalCacheStore build(CacheScope cacheScope) {
        if (cacheScope == null) {
            throw new IllegalStateException("cacheScope must not be null");
        }

        if (cacheStore == null) {
            throw new IllegalStateException("cacheStore must not be null");
        }

        return new DefaultHierarchicalCacheStore(cacheScope, cacheStore);
    }

    @Override
    public DefaultHierarchicalCacheStore build(@NonNull String scope) {
        return build(CacheScope.of(scope));
    }
}
