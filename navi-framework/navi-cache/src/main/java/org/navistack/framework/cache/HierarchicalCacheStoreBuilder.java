package org.navistack.framework.cache;

import lombok.NonNull;

public interface HierarchicalCacheStoreBuilder {

    HierarchicalCacheStore build(CacheScope scope);

    default HierarchicalCacheStore build(@NonNull String scope) {
        return build(CacheScope.of(scope));
    }
}
