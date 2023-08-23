package org.navistack.framework.core;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@UtilityClass
public class ErrCodes {
    private final Map<Class<? extends ErrCategory>, ErrCategory> categoryCache = new ConcurrentHashMap<>();

    public ErrCode of(int value, ErrCategory category) {
        return new ErrCodeImpl(value, category);
    }

    public ErrCode of(int value, Class<? extends ErrCategory> clazz) {
        return new ErrCodeImpl(value, getErrCategory(clazz));
    }

    public <T extends Enum<T>> ErrCode of(T value, ErrCategory category) {
        return new ErrCodeImpl(value.ordinal(), category);
    }

    public <T extends Enum<T>> ErrCode of(T value, Class<? extends ErrCategory> clazz) {
        return new ErrCodeImpl(value.ordinal(), getErrCategory(clazz));
    }

    public <T extends Numeric<Integer>> ErrCode of(T value, ErrCategory category) {
        return new ErrCodeImpl(value.numeral(), category);
    }

    public <T extends Numeric<Integer>> ErrCode of(T value, Class<? extends ErrCategory> clazz) {
        return new ErrCodeImpl(value.numeral(), getErrCategory(clazz));
    }

    @SuppressWarnings("unchecked")
    public <T extends ErrCategory> T getErrCategory(Class<T> clazz) {
        return (T) categoryCache.computeIfAbsent(clazz, new Function<>() {
            @Override
            @SneakyThrows
            public ErrCategory apply(Class<? extends ErrCategory> clazz) {
                return clazz.getDeclaredConstructor()
                        .newInstance();
            }
        });
    }
}
