package org.navistack.framework.core.error;

import lombok.Getter;

public class CodedException extends RuntimeException {
    @Getter
    private final int errorCode;

    public CodedException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CodedException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CodedException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CodedException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    protected CodedException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
