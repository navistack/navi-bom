package org.navistack.framework.objectstorage;

public class FileSizeLimitExceededException extends FileUploadPolicyViolationException {
    public FileSizeLimitExceededException() {
        super();
    }

    public FileSizeLimitExceededException(String message) {
        super(message);
    }

    public FileSizeLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeLimitExceededException(Throwable cause) {
        super(cause);
    }

    protected FileSizeLimitExceededException(String message,
                                             Throwable cause,
                                             boolean enableSuppression,
                                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
