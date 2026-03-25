package org.navistack.framework.cache;

import lombok.NonNull;

public interface ScopedCacheStoreBuilder {

    CacheStore build(CacheScope scope);

    default CacheStore build(@NonNull String scope) {
        return build(CacheScope.of(scope));
    }
}
