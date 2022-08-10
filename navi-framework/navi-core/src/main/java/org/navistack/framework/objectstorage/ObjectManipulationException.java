package org.navistack.framework.objectstorage;

public class ObjectManipulationException extends RuntimeException {
    public ObjectManipulationException() {
        super();
    }

    public ObjectManipulationException(String message) {
        super(message);
    }

    public ObjectManipulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectManipulationException(Throwable cause) {
        super(cause);
    }

    protected ObjectManipulationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
