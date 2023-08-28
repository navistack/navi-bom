package org.navistack.framework.cache;

public interface ScopedCacheServiceBuilder {
    CacheService build(String... namespace);
}
