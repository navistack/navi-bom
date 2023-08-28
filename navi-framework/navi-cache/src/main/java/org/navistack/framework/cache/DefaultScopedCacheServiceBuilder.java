package org.navistack.framework.cache;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.utils.Strings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultScopedCacheServiceBuilder implements ScopedCacheServiceBuilder {
    @Getter
    private final CacheService cacheService;

    @Getter
    @Setter
    private String delimiter = ".";

    private final Map<String, CacheService> serviceCache = new ConcurrentHashMap<>();

    public DefaultScopedCacheServiceBuilder(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public CacheService build(String... namespace) {
        String unifiedNamespace = Strings.join(delimiter, namespace);
        if (!Strings.hasText(unifiedNamespace)) {
            throw new IllegalArgumentException("namespace must not be empty");
        }
        return serviceCache.computeIfAbsent(unifiedNamespace, k -> {
            CacheKeyBuilder keyBuilder = new PrefixedCacheKeyBuilder(delimiter, k);
            return new ScopedCacheService(cacheService, keyBuilder);
        });
    }
}
