package org.navistack.framework.objectstorage;

public class BucketManipulationException extends ObjectStorageException {
    public BucketManipulationException() {
        super();
    }

    public BucketManipulationException(String message) {
        super(message);
    }

    public BucketManipulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BucketManipulationException(Throwable cause) {
        super(cause);
    }

    protected BucketManipulationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
