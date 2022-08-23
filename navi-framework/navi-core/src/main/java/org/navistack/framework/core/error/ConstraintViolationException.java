package org.navistack.framework.core.error;

public class ConstraintViolationException extends DomainException {
    public ConstraintViolationException() {
        super();
    }

    public ConstraintViolationException(int errorCode) {
        super(errorCode);
    }

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintViolationException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public ConstraintViolationException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    protected ConstraintViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected ConstraintViolationException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorCode, message, cause, enableSuppression, writableStackTrace);
    }
}
