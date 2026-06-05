package org.navistack.framework.cache;

public interface HierarchicalCacheStore extends CacheStore {

    CacheStore parentCacheStore();
}
