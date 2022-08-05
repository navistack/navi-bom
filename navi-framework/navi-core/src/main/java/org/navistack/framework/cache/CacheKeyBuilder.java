package org.navistack.framework.cache;

public interface CacheKeyBuilder {
    String build(String part, String... extraParts);
}
