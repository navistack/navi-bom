package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppErrors {
    /**
     * Unspecific error.
     */
    public final int UNSPECIFIC_ERROR = ErrorCodes.globalAppError(0x00);

    /**
     * Specific error.
     */
    public AppException specificError(int error) {
        return new AppException(ErrorCodes.appError(error));
    }

    /**
     * Specific error.
     */
    public AppException specificError(int error, String message) {
        return new AppException(ErrorCodes.appError(error), message);
    }

    /**
     * Specific error.
     */
    public AppException specificError(int error, String message, Throwable throwable) {
        return new AppException(ErrorCodes.appError(error), message, throwable);
    }

    /**
     * Specific error.
     */
    public AppException specificError(int error, Throwable throwable) {
        return new AppException(ErrorCodes.appError(error), throwable);
    }

    /**
     * Unspecific error.
     */
    public AppException unspecificError() {
        return new AppException(UNSPECIFIC_ERROR);
    }

    /**
     * Unspecific error.
     */
    public AppException unspecificError(String message) {
        return new AppException(UNSPECIFIC_ERROR, message);
    }

    /**
     * Unspecific error.
     */
    public AppException unspecificError(Throwable throwable) {
        return new AppException(UNSPECIFIC_ERROR, throwable);
    }
}
