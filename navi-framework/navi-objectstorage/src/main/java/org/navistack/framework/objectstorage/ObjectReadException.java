package org.navistack.framework.objectstorage;

public class ObjectReadException extends ObjectManipulationException {
    public ObjectReadException() {
        super();
    }

    public ObjectReadException(String message) {
        super(message);
    }

    public ObjectReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectReadException(Throwable cause) {
        super(cause);
    }

    protected ObjectReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
