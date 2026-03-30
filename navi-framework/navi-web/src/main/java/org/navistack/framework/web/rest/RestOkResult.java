package org.navistack.framework.web.rest;

public interface RestOkResult<T> extends RestResult<T> {

    @Override
    default boolean isSucceeded() {
        return true;
    }

    @Override
    default int getStatus() {
        return 200;
    }

    T getResult();
}
