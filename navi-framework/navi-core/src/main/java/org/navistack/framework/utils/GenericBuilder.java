package org.navistack.framework.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {
    private final Supplier<T> supplier;

    private final Collection<Consumer<T>> setters = new ArrayList<>();

    public GenericBuilder(Supplier<T> supplier) {
        if (supplier == null) {
            throw new NullPointerException("supplier must not be null");
        }
        this.supplier = supplier;
    }

    public <U> GenericBuilder<T> set(BiConsumer<T, U> setter, U value) {
        if (setter == null) {
            throw new NullPointerException("setter must not be null");
        }
        setters.add(object -> setter.accept(object, value));
        return this;
    }

    public T build() {
        T object = supplier.get();
        if (object == null) {
            throw new NullPointerException("supplier must not return null");
        }
        setters.forEach(consumer -> consumer.accept(object));
        return object;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> supplier) {
        return new GenericBuilder<>(supplier);
    }
}
