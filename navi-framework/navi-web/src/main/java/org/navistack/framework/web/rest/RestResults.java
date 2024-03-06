package org.navistack.framework.web.rest;

import lombok.experimental.UtilityClass;
import org.navistack.framework.core.error.CodedException;
import org.springframework.http.HttpStatus;

@UtilityClass
public class RestResults {
    public <T> RestOkResult<T> ok() {
        return new RestOkResultImpl<>();
    }

    public <T> RestOkResult<T> ok(T result) {
        return new RestOkResultImpl<>(result);
    }

    public RestErrResult err(int error, String message, HttpStatus status) {
        return new RestErrResultImpl()
                .setError(error)
                .setMessage(message)
                .setStatus(status);
    }

    public RestErrResult err(int error, String message) {
        return new RestErrResultImpl()
                .setError(error)
                .setMessage(message);
    }

    public RestErrResult err(String message) {
        return new RestErrResultImpl()
                .setMessage(message);
    }

    public RestErrResult err(CodedException exception) {
        return new RestErrResultImpl()
                .setError(exception.getErrorCode())
                .setMessage(exception.getMessage())
                .setThrowable(exception);
    }

    public RestErrResult err(Throwable throwable) {
        return new RestErrResultImpl()
                .setMessage(throwable.getMessage())
                .setThrowable(throwable);
    }
}
