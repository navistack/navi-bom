package org.navistack.framework.security;

public class TokenServiceIssueException extends RuntimeException {
    public TokenServiceIssueException() {
        super();
    }

    public TokenServiceIssueException(String message) {
        super(message);
    }

    public TokenServiceIssueException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenServiceIssueException(Throwable cause) {
        super(cause);
    }

    protected TokenServiceIssueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
