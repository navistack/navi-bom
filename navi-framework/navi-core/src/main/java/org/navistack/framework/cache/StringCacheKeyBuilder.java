package org.navistack.framework.cache;

import lombok.Getter;
import org.navistack.framework.utils.Strings;

import java.util.StringJoiner;

@Getter
public class StringCacheKeyBuilder implements CacheKeyBuilder<String> {
    private final static String DEFAULT_PREFIX = "";
    private final static String DEFAULT_SUFFIX = "";
    private final static String DEFAULT_DELIMITER = ".";

    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public StringCacheKeyBuilder(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCacheKeyBuilder(String delimiter, String prefix) {
        this(delimiter, prefix, DEFAULT_SUFFIX);
    }

    public StringCacheKeyBuilder(String delimiter, String... prefixes) {
        this(delimiter, buildCompositePrefix(delimiter, prefixes), DEFAULT_SUFFIX);
    }

    public StringCacheKeyBuilder(String delimiter) {
        this(delimiter, DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    @Override
    public String build(String part, String... extraParts) {
        StringJoiner joiner = new StringJoiner(delimiter);
        if (Strings.hasLength(prefix)) {
            joiner.add(prefix);
        }
        joiner.add(part);
        for (String extraPart : extraParts) {
            joiner.add(extraPart);
        }
        if (Strings.hasLength(suffix)) {
            joiner.add(suffix);
        }
        return joiner.toString();
    }

    private String buildCompositePrefix(String... prefixes) {
        return buildCompositePrefix(delimiter, prefixes);
    }

    private static String buildCompositePrefix(String delimiter, String... prefixes) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (String prefix : prefixes) {
            joiner.add(prefix);
        }
        return joiner.toString();
    }
}
