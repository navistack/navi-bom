package org.navistack.framework.core.error;

public class EntityDuplicationException extends ConstraintViolationException {
    public EntityDuplicationException() {
        super(DomainErrorCodes.ENTITY_DUPLICATION);
    }

    public EntityDuplicationException(String message) {
        super(DomainErrorCodes.ENTITY_DUPLICATION, message);
    }

    public EntityDuplicationException(String message, Throwable cause) {
        super(DomainErrorCodes.ENTITY_DUPLICATION, message, cause);
    }

    public EntityDuplicationException(Throwable cause) {
        super(DomainErrorCodes.ENTITY_DUPLICATION, cause);
    }

    protected EntityDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(DomainErrorCodes.ENTITY_DUPLICATION, message, cause, enableSuppression, writableStackTrace);
    }
}
