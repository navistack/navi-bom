package org.navistack.framework.locking;

public class LockAcquisitionFailureException extends RuntimeException {
    public LockAcquisitionFailureException() {
        super();
    }

    public LockAcquisitionFailureException(String message) {
        super(message);
    }

    public LockAcquisitionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockAcquisitionFailureException(Throwable cause) {
        super(cause);
    }

    protected LockAcquisitionFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
