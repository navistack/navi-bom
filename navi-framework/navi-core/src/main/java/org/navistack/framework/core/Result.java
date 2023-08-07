package org.navistack.framework.core;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Result<T, E> {
    Optional<T> ok();

    Optional<E> err();

    T unwrap();

    T unwrap(T defaultValue);

    T unwrap(Supplier<? extends T> valueSupplier);

    E unwrapErr();

    void throwErr();

    <F extends RuntimeException> void throwErr(Function<E, F> converter);

    boolean isOk();

    boolean isOk(T value);

    boolean isOk(Class<? super T> valueClass);

    boolean isOk(Predicate<? super T> pred);

    boolean isErr();

    boolean isErr(E err);

    boolean isErr(Class<? super E> errClass);

    boolean isErr(Predicate<? super E> pred);

    <U> Result<U, E> map(Function<T, U> mapper);

    <F> Result<T, F> mapErr(Function<E, F> mapper);

    Result<T, E> inspect(Consumer<T> inspector);

    <U> Result<U, E> and(Result<U, E> other);

    <U> Result<U, E> and(Function<T, Result<U, E>> fn);

    <F> Result<T, F> or(Result<T, F> other);

    <F> Result<T, F> or(Function<E, Result<T, F>> fn);
}
