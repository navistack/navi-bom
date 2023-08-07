package org.navistack.framework.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Accessors(fluent = true)
public class OkImpl<T, E> implements Ok<T, E> {
    protected T value;

    @Override
    public Optional<T> ok() {
        return Optional.ofNullable(value);
    }

    @Override
    public Optional<E> err() {
        return Optional.empty();
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public T unwrap(T defaultValue) {
        return value;
    }

    @Override
    public T unwrap(Supplier<? extends T> valueSupplier) {
        return value;
    }

    @Override
    public E unwrapErr() {
        throw new IllegalStateException("Attempted to call unwrapErr on a \"Ok\" value");
    }

    @Override
    public void throwErr() {
        throw new IllegalStateException("Attempted to call throwErr on a \"Ok\" value");
    }

    @Override
    public <F extends RuntimeException> void throwErr(Function<E, F> converter) {
        throw new IllegalStateException("Attempted to call throwErr on a \"Ok\" value");
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isOk(T value) {
        return value != null && value.equals(this.value);
    }

    @Override
    public boolean isOk(Class<? super T> valueClass) {
        return valueClass != null && valueClass.isInstance(value);
    }

    @Override
    public boolean isOk(Predicate<? super T> pred) {
        return pred.test(value);
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public boolean isErr(E err) {
        return false;
    }

    @Override
    public boolean isErr(Class<? super E> errClass) {
        return false;
    }

    @Override
    public boolean isErr(Predicate<? super E> pred) {
        return false;
    }

    @Override
    public <U> Ok<U, E> map(Function<T, U> mapper) {
        return new OkImpl<>(mapper.apply(value));
    }

    @Override
    public <F> Ok<T, F> mapErr(Function<E, F> mapper) {
        return new OkImpl<>(value);
    }

    @Override
    public Result<T, E> inspect(Consumer<T> inspector) {
        inspector.accept(value);
        return this;
    }
}
