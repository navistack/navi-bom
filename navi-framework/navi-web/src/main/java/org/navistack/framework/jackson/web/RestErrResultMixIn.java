package org.navistack.framework.jackson.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface RestErrResultMixIn<E> {
    boolean isSucceeded();

    int getError();

    String getMessage();

    String getEndpoint();

    @JsonUnwrapped
    Map<String, Object> getParameters();

    @JsonIgnore
    HttpStatus getStatus();

    @JsonIgnore
    Throwable getThrowable();
}
