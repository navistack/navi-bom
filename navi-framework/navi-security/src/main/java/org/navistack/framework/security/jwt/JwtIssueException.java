package org.navistack.framework.security.jwt;

public class JwtIssueException extends RuntimeException {
    public JwtIssueException() {
        super();
    }

    public JwtIssueException(String message) {
        super(message);
    }

    public JwtIssueException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtIssueException(Throwable cause) {
        super(cause);
    }

    protected JwtIssueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
