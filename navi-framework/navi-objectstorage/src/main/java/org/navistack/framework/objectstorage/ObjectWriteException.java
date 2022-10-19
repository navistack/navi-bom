package org.navistack.framework.objectstorage;

public class ObjectWriteException extends ObjectManipulationException {
    public ObjectWriteException() {
        super();
    }

    public ObjectWriteException(String message) {
        super(message);
    }

    public ObjectWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectWriteException(Throwable cause) {
        super(cause);
    }

    protected ObjectWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
