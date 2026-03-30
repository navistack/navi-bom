package org.navistack.framework.web.rest;

public interface RestResult<T> {
    boolean isSucceeded();

    int getStatus();
}
