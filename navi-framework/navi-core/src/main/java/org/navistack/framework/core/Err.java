package org.navistack.framework.core;

import java.util.function.Function;

public interface Err<T, E> extends Result<T, E> {
    @Override
    <U> Err<U, E> map(Function<T, U> mapper);

    @Override
    <F> Err<T, F> mapErr(Function<E, F> mapper);
}
