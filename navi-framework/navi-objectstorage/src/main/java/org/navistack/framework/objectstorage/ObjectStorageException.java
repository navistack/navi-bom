package org.navistack.framework.objectstorage;

public class ObjectStorageException extends RuntimeException {
    public ObjectStorageException() {
        super();
    }

    public ObjectStorageException(String message) {
        super(message);
    }

    public ObjectStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectStorageException(Throwable cause) {
        super(cause);
    }

    protected ObjectStorageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
