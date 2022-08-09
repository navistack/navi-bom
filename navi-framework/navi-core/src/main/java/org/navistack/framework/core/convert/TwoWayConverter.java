package org.navistack.framework.core.convert;

import lombok.NonNull;

public interface TwoWayConverter<S, T> extends Converter<S, T> {
    T convert(S src);

    S convertBack(T dst);

    default <U> TwoWayConverter<S, U> andThen(@NonNull TwoWayConverter<T, U> converter) {
        return new TwoWayConverter<S, U>() {
            @Override
            public U convert(S src) {
                T t = TwoWayConverter.this.convert(src);
                return t == null ? null : converter.convert(t);
            }

            @Override
            public S convertBack(U dst) {
                T t = converter.convertBack(dst);
                return t == null ? null : TwoWayConverter.this.convertBack(t);
            }
        };
    }

    default Converter<S, T> converter() {
        return this::convert;
    }

    default Converter<T, S> backwardConverter() {
        return this::convertBack;
    }
}
