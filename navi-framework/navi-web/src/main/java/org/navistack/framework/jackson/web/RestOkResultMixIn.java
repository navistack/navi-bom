package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface RestOkResultMixIn<T> {
    boolean isSucceeded();

    T getResult();
}
