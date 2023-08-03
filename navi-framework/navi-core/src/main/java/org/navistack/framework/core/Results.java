package org.navistack.framework.core;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Results {
    private final static Result<?, ?> EMPTY_OK = new OkImpl<>(null);

    public <T, E> Result<T, E> ok() {
        @SuppressWarnings("unchecked")
        Result<T, E> r = (Result<T, E>) EMPTY_OK;
        return r;
    }

    public <T, E> Result<T, E> ok(T value) {
        if (value == null) {
            return ok();
        }
        return new OkImpl<>(value);
    }

    private final static Result<?, ?> EMPTY_ERR = new ErrImpl<>(null);

    public <T, E> Result<T, E> err() {
        @SuppressWarnings("unchecked")
        Result<T, E> r = (Result<T, E>) EMPTY_ERR;
        return r;
    }

    public <T, E> Result<T, E> err(E err) {
        if (err == null) {
            return err();
        }
        return new ErrImpl<>(err);
    }
}
