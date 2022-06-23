package org.navistack.boot.autoconfigure.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface RestResultMixIn<T, E> {
    boolean isSucceeded();

    T getResult();

    @JsonUnwrapped
    E getError();
}
