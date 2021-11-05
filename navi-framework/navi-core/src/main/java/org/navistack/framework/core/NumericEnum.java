package org.navistack.framework.core;

import java.util.Optional;

public interface NumericEnum {
    Integer number();

    static <T extends Enum<T> & NumericEnum> T valueOf(Class<T> tClass, Integer value) {
        return optionalValueOf(tClass, value)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No enum constant " + tClass.getCanonicalName() + "." + value
                        )
                );
    }

    static <T extends Enum<T> & NumericEnum> T ValueOf(Class<T> tClass, Integer value, T defaultValue) {
        return optionalValueOf(tClass, value).orElse(defaultValue);
    }

    static <T extends Enum<T> & NumericEnum> Optional<T> optionalValueOf(Class<T> tClass, Integer value) {
        return Optional.ofNullable(nullableValueOf(tClass, value));
    }

    static <T extends Enum<T> & NumericEnum> T nullableValueOf(Class<T> tClass, Integer value) {
        if (value == null)
            throw new NullPointerException("value is null");
        return NumericEnumCache.enumConstantDirectory(tClass).get(value);
    }
}
