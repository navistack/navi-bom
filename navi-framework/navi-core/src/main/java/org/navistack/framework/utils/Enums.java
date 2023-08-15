package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;
import org.navistack.framework.core.Numeric;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class Enums {
    public <T extends Enum<T> & Numeric<? extends Number>> T valueOf(Class<T> tClass, Integer value) {
        return optionalValueOf(tClass, value)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No enum constant " + tClass.getCanonicalName() + "." + value
                        )
                );
    }

    public <T extends Enum<T>> T valueOf(Class<T> tClass, String name) {
        return optionalValueOf(tClass, name)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No enum constant " + tClass.getCanonicalName() + "." + name
                        )
                );
    }

    public <T extends Enum<T> & Numeric<? extends Number>> T valueOf(Class<T> tClass, Integer value, T defaultEnum) {
        return optionalValueOf(tClass, value).orElse(defaultEnum);
    }

    public <T extends Enum<T>> T valueOf(Class<T> tClass, String name, T defaultEnum) {
        return optionalValueOf(tClass, name).orElse(defaultEnum);
    }

    public <T extends Enum<T> & Numeric<? extends Number>> Optional<T> optionalValueOf(Class<T> tClass, Integer value) {
        return Optional.ofNullable(nullableValueOf(tClass, value));
    }

    public <T extends Enum<T>> Optional<T> optionalValueOf(Class<T> tClass, String name) {
        return Optional.ofNullable(nullableValueOf(tClass, name));
    }

    public <T extends Enum<T> & Numeric<? extends Number>> T nullableValueOf(Class<T> tClass, Integer value) {
        if (tClass == null)
            throw new NullPointerException("tClass is null");
        return numericEnumConstantDirectory(tClass).get(value);
    }

    public <T extends Enum<T>> T nullableValueOf(Class<T> tClass, String name) {
        if (tClass == null)
            throw new NullPointerException("tClass is null");
        return enumConstantDirectory(tClass).get(name);
    }

    private final Map<Class<? extends Enum<?>>, Map<Object, ? extends Enum<?>>> CONSTANT_DIRECTORY = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    <T extends Enum<T>> Map<Object, T> enumConstantDirectory(Class<T> tClass) {
        return (Map<Object, T>) CONSTANT_DIRECTORY.computeIfAbsent(tClass,
                k -> {
                    T[] constants = tClass.getEnumConstants();
                    Map<Object, T> constantDirectory = new HashMap<>((int) (constants.length / 0.75f) + 1);
                    for (T constant : constants) {
                        constantDirectory.put(constant.name(), constant);
                    }
                    return constantDirectory;
                });
    }

    @SuppressWarnings("unchecked")
    <T extends Enum<T> & Numeric<? extends Number>> Map<Object, T> numericEnumConstantDirectory(Class<T> tClass) {
        return (Map<Object, T>) CONSTANT_DIRECTORY.computeIfAbsent(tClass,
                k -> {
                    T[] constants = tClass.getEnumConstants();
                    Map<Object, T> constantDirectory = new HashMap<>((int) (constants.length / 0.75f) + 1);
                    for (T constant : constants) {
                        constantDirectory.put(constant.numeral(), constant);
                    }
                    return constantDirectory;
                });
    }
}
