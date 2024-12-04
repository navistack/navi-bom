package org.navistack.framework.core.error;

import lombok.Getter;

@Getter
public class CodedException extends RuntimeException {
    private int code;

    public CodedException() {
        super();
    }

    public CodedException(int code) {
        super();
        this.code = code;
    }

    public CodedException(String message) {
        super(message);
    }

    public CodedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CodedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodedException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CodedException(Throwable cause) {
        super(cause);
    }

    public CodedException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    protected CodedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected CodedException(int code, String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
