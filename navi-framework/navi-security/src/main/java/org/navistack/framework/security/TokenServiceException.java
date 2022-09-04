package org.navistack.framework.security;

public class TokenServiceException extends RuntimeException {
    public TokenServiceException() {
        super();
    }

    public TokenServiceException(String message) {
        super(message);
    }

    public TokenServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenServiceException(Throwable cause) {
        super(cause);
    }

    protected TokenServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
