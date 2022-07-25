package org.navistack.framework.security.jwt;

public class JwtTokenServiceException extends RuntimeException {
    public JwtTokenServiceException() {
        super();
    }

    public JwtTokenServiceException(String message) {
        super(message);
    }

    public JwtTokenServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenServiceException(Throwable cause) {
        super(cause);
    }

    protected JwtTokenServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
