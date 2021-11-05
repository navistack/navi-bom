package org.navistack.framework.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class NumericEnumCache {
    private NumericEnumCache() {}

    private static final Map<Class<?>, Map<Integer, ?>> CONSTANT_DIRECTORY = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    static <T extends Enum<T> & NumericEnum> Map<Integer, T> enumConstantDirectory(Class<T> tClass) {
        return (Map<Integer, T>) NumericEnumCache.CONSTANT_DIRECTORY.computeIfAbsent(tClass,
                k -> {
                    Map<Integer, T> enumConstantDirectory = new HashMap<>();
                    for (T enumConstant : tClass.getEnumConstants()) {
                        enumConstantDirectory.put(enumConstant.number(), enumConstant);
                    }

                    return enumConstantDirectory;
                });
    }
}
