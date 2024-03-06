package org.navistack.framework.web.rest;

import org.springframework.http.HttpStatus;

public interface RestResult<T> {
    boolean isSucceeded();

    HttpStatus getStatus();
}
