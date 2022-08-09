package org.navistack.framework.core.convert;

import lombok.NonNull;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S src);

    default <U> Converter<S, U> andThen(@NonNull Converter<? super T, ? extends U> converter) {
        return (S s) -> {
            T t = convert(s);
            return t == null ? null : converter.convert(t);
        };
    }
}
