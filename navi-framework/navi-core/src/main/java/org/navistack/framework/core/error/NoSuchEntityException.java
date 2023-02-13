package org.navistack.framework.core.error;

public class NoSuchEntityException extends DomainException {
    private static final int ERROR_CODE = DomainErrorCodes.NO_SUCH_ENTITY;

    public NoSuchEntityException() {
        super(ERROR_CODE);
    }

    public NoSuchEntityException(String message) {
        super(ERROR_CODE, message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public NoSuchEntityException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    protected NoSuchEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ERROR_CODE, message, cause, enableSuppression, writableStackTrace);
    }
}
