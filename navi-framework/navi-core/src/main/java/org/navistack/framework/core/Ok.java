package org.navistack.framework.core;

import java.util.function.Function;

public interface Ok<T, E> extends Result<T, E> {
    @Override
    <U> Ok<U, E> map(Function<T, U> mapper);

    @Override
    <F> Ok<T, F> mapErr(Function<E, F> mapper);
}
