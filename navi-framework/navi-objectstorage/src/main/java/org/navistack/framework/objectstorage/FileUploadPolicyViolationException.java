package org.navistack.framework.objectstorage;

public class FileUploadPolicyViolationException extends FileUploadException {
    public FileUploadPolicyViolationException() {
        super();
    }

    public FileUploadPolicyViolationException(String message) {
        super(message);
    }

    public FileUploadPolicyViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadPolicyViolationException(Throwable cause) {
        super(cause);
    }

    protected FileUploadPolicyViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
