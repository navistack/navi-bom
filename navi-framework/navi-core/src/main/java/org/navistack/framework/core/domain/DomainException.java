package org.navistack.framework.core.domain;

import lombok.Getter;

public class DomainException extends RuntimeException {
    @Getter
    private final int errorCode;

    public DomainException(int errorCode) {
        super(errorCode + " - Error occurred");
        this.errorCode = errorCode;
    }

    public DomainException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DomainException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    protected DomainException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
