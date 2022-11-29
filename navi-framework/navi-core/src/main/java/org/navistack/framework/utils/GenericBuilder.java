package org.navistack.framework.utils;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {
    private final T object;

    public GenericBuilder(Supplier<T> supplier) {
        if (supplier == null) {
            throw new NullPointerException("supplier must not be null");
        }
        T object = supplier.get();
        if (object == null) {
            throw new NullPointerException("supplier must not return null");
        }
        this.object = object;
    }

    public <U> GenericBuilder<T> set(BiConsumer<T, U> setter, U value) {
        setter.accept(object, value);
        return this;
    }

    public T build() {
        return object;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> supplier) {
        return new GenericBuilder<>(supplier);
    }
}
