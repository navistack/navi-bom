package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;
import org.navistack.framework.core.Numeric;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class Enums {
    public <T extends Enum<T> & Numeric<? extends Number>> T valueOf(Class<T> clazz, Integer value) {
        return optionalValueOf(clazz, value)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No enum constant " + clazz.getCanonicalName() + "." + value
                        )
                );
    }

    public <T extends Enum<T>> T valueOf(Class<T> clazz, String name) {
        return optionalValueOf(clazz, name)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No enum constant " + clazz.getCanonicalName() + "." + name
                        )
                );
    }

    public <T extends Enum<T> & Numeric<? extends Number>> T valueOf(Class<T> clazz, Integer value, T defaultEnum) {
        return optionalValueOf(clazz, value).orElse(defaultEnum);
    }

    public <T extends Enum<T>> T valueOf(Class<T> clazz, String name, T defaultEnum) {
        return optionalValueOf(clazz, name).orElse(defaultEnum);
    }

    public <T extends Enum<T> & Numeric<? extends Number>> Optional<T> optionalValueOf(Class<T> clazz, Integer value) {
        return Optional.ofNullable(nullableValueOf(clazz, value));
    }

    public <T extends Enum<T>> Optional<T> optionalValueOf(Class<T> clazz, String name) {
        return Optional.ofNullable(nullableValueOf(clazz, name));
    }

    public <T extends Enum<T> & Numeric<? extends Number>> T nullableValueOf(Class<T> clazz, Integer value) {
        if (clazz == null) {
            throw new NullPointerException("clazz is null");
        }
        return numericEnumConstantDirectory(clazz).get(value);
    }

    public <T extends Enum<T>> T nullableValueOf(Class<T> clazz, String name) {
        if (clazz == null) {
            throw new NullPointerException("clazz is null");
        }
        return enumConstantDirectory(clazz).get(name);
    }

    private final Map<Class<? extends Enum<?>>, Map<Object, ? extends Enum<?>>>
            CONSTANT_DIRECTORY = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    <T extends Enum<T>> Map<Object, T> enumConstantDirectory(Class<T> clazz) {
        return (Map<Object, T>) CONSTANT_DIRECTORY.computeIfAbsent(clazz,
                k -> {
                    T[] constants = clazz.getEnumConstants();
                    Map<Object, T> constantDirectory = new HashMap<>((int) (constants.length / 0.75f) + 1);
                    for (T constant : constants) {
                        constantDirectory.put(constant.name(), constant);
                    }
                    return constantDirectory;
                });
    }

    @SuppressWarnings("unchecked")
    <T extends Enum<T> & Numeric<? extends Number>> Map<Object, T> numericEnumConstantDirectory(Class<T> clazz) {
        return (Map<Object, T>) CONSTANT_DIRECTORY.computeIfAbsent(clazz,
                k -> {
                    T[] constants = clazz.getEnumConstants();
                    Map<Object, T> constantDirectory = new HashMap<>((int) (constants.length / 0.75f) + 1);
                    for (T constant : constants) {
                        constantDirectory.put(constant.numeral(), constant);
                    }
                    return constantDirectory;
                });
    }
}
