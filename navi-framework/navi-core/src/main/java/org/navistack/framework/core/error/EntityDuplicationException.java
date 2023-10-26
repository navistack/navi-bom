package org.navistack.framework.core.error;

public class EntityDuplicationException extends DomainException {
    private static final int ERROR_CODE = DomainErrorCodes.ENTITY_DUPLICATION;

    public EntityDuplicationException() {
        super(ERROR_CODE);
    }

    public EntityDuplicationException(String message) {
        super(ERROR_CODE, message);
    }

    public EntityDuplicationException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public EntityDuplicationException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    protected EntityDuplicationException(String message,
                                         Throwable cause,
                                         boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(ERROR_CODE, message, cause, enableSuppression, writableStackTrace);
    }
}
