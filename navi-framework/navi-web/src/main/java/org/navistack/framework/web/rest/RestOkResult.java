package org.navistack.framework.web.rest;

import org.springframework.http.HttpStatus;

public interface RestOkResult<T> extends RestResult<T> {

    @Override
    default boolean isSucceeded() {
        return true;
    }

    @Override
    default HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    T getResult();
}
