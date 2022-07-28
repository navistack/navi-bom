package org.navistack.framework.cache;

public interface CacheKeyBuilder<T> {
    T build(T part, T... extraParts);
}
