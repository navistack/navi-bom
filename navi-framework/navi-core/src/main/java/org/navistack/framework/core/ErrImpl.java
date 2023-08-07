package org.navistack.framework.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Lombok;
import lombok.experimental.Accessors;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Accessors(fluent = true)
@EqualsAndHashCode
public class ErrImpl<T, E> implements Err<T, E> {
    protected E err;

    @Override
    public Optional<T> ok() {
        return Optional.empty();
    }

    @Override
    public Optional<E> err() {
        return Optional.ofNullable(err);
    }

    @Override
    public T unwrap() {
        throw new IllegalStateException("Attempted to call unwrap on a \"err\" value");
    }

    @Override
    public T unwrap(T defaultValue) {
        return defaultValue;
    }

    @Override
    public E unwrapErr() {
        return err;
    }

    @Override
    public T unwrap(Supplier<? extends T> valueSupplier) {
        return valueSupplier.get();
    }

    @Override
    public void throwErr() {
        if (err == null) {
            throw new NullPointerException("err is null");
        } else if (err instanceof Throwable ex) {
            throw Lombok.sneakyThrow(ex);
        } else {
            throw new RuntimeException(err.toString());
        }
    }

    @Override
    public <F extends RuntimeException> void throwErr(Function<E, F> converter) {
        throw converter.apply(err);
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isOk(T value) {
        return false;
    }

    @Override
    public boolean isOk(Class<? super T> valueClass) {
        return false;
    }

    @Override
    public boolean isOk(Predicate<? super T> pred) {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public boolean isErr(E err) {
        return err != null && err.equals(this.err);
    }

    @Override
    public boolean isErr(Class<? super E> errClass) {
        return errClass != null && errClass.isInstance(err);
    }

    @Override
    public boolean isErr(Predicate<? super E> pred) {
        return pred.test(err);
    }

    @Override
    public <U> Err<U, E> map(Function<T, U> mapper) {
        return new ErrImpl<>(err);
    }

    @Override
    public <F> Err<T, F> mapErr(Function<E, F> mapper) {
        return new ErrImpl<>(mapper.apply(err));
    }

    @Override
    public Result<T, E> inspect(Consumer<T> inspector) {
        return this;
    }

    @Override
    public <U> Result<U, E> and(Result<U, E> other) {
        return new ErrImpl<>(err);
    }

    @Override
    public <U> Result<U, E> and(Function<T, Result<U, E>> fn) {
        return new ErrImpl<>(err);
    }

    @Override
    public <F> Result<T, F> or(Result<T, F> other) {
        return other;
    }

    @Override
    public <F> Result<T, F> or(Function<E, Result<T, F>> fn) {
        return fn.apply(err);
    }
}
