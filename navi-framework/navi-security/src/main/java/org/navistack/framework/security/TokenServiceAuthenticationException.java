package org.navistack.framework.security;

public class TokenServiceAuthenticationException extends RuntimeException {
    public TokenServiceAuthenticationException() {
        super();
    }

    public TokenServiceAuthenticationException(String message) {
        super(message);
    }

    public TokenServiceAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenServiceAuthenticationException(Throwable cause) {
        super(cause);
    }

    protected TokenServiceAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
