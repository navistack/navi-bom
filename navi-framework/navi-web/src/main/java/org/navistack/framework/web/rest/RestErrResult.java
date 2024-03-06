package org.navistack.framework.web.rest;

import org.springframework.http.HttpStatus;

import java.util.Map;

public interface RestErrResult extends RestResult<Void> {

    @Override
    default boolean isSucceeded() {
        return false;
    }

    int getError();

    RestErrResult setError(int error);

    String getMessage();

    RestErrResult setMessage(String message);


    String getEndpoint();

    RestErrResult setEndpoint(String endpoint);

    Map<String, Object> getParameters();

    RestErrResult setParameters(Map<String, Object> parameters);

    Object getParameter(String key);

    @SuppressWarnings("unchecked")
    default <T> T getParameter(String key, Class<T> targetType) {
        return (T) getParameter(key);
    }

    Object getParameter(String key, Object defaultValue);

    @SuppressWarnings("unchecked")
    default <T> T getParameter(String key, Object defaultValue, Class<T> targetType) {
        return (T) getParameter(key, defaultValue);
    }

    RestErrResult putParameter(String key, Object value);

    RestErrResult putParameters(Map<String, Object> parameters);

    RestErrResult setStatus(HttpStatus status);

    Throwable getThrowable();

    RestErrResult setThrowable(Throwable throwable);
}
