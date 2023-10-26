package org.navistack.framework.objectstorage;

public class InvalidContentTypeException extends FileUploadPolicyViolationException {
    public InvalidContentTypeException() {
        super();
    }

    public InvalidContentTypeException(String message) {
        super(message);
    }

    public InvalidContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidContentTypeException(Throwable cause) {
        super(cause);
    }

    protected InvalidContentTypeException(String message,
                                          Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
