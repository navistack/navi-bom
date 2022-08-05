package org.navistack.framework.cache;

import lombok.Getter;
import org.navistack.framework.utils.Strings;

import java.util.StringJoiner;

@Getter
public class PrefixedCacheKeyBuilder implements CacheKeyBuilder {
    private final static String DEFAULT_PREFIX = "";
    private final static String DEFAULT_DELIMITER = ".";

    private final String delimiter;
    private final String prefix;

    public PrefixedCacheKeyBuilder(String delimiter, String prefix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
    }

    public PrefixedCacheKeyBuilder(String delimiter, String... prefixes) {
        this(delimiter, buildCompositePrefix(delimiter, prefixes));
    }

    public PrefixedCacheKeyBuilder(String delimiter) {
        this(delimiter, DEFAULT_PREFIX);
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
