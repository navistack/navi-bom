package org.navistack.framework.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.StringJoiner;

@AllArgsConstructor
public class CacheScope {

    private static final String DEFAULT_NAME = "";
    private static final String DEFAULT_DELIMITER = ":";

    @Getter
    private final String delimiter;

    @Getter
    private final String name;

    @Getter
    private final CacheScope scope;

    public CacheScope(String name) {
        this(DEFAULT_DELIMITER, name, null);
    }

    public CacheScope() {
        this(DEFAULT_DELIMITER, DEFAULT_NAME, null);
    }

    public CacheScope scope(String name) {
        return new CacheScope(delimiter, name, this);
    }

    public String key(String part, String... extraParts) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CacheScope scope = this; scope != null; scope = scope.scope) {
            String scopeName = scope.name;
            if (scopeName != null && !scopeName.isEmpty()) {
                joiner.add(scopeName);
            }
        }
        joiner.add(part);
        for (String extraPart : extraParts) {
            joiner.add(extraPart);
        }
        return joiner.toString();
    }

    public static CacheScope of(String name) {
        return new CacheScope(name);
    }

    public static CacheScope root() {
        return new CacheScope();
    }
}
