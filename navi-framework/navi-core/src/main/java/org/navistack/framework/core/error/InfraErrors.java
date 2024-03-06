package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InfraErrors {
    /**
     * Unspecific error.
     */
    public final int UNSPECIFIC_ERROR = ErrorCodes.globalInfraError(0x00);

    /**
     * Specific error.
     */
    public InfraException specificError(int error) {
        return new InfraException(ErrorCodes.appError(error));
    }

    /**
     * Specific error.
     */
    public InfraException specificError(int error, String message) {
        return new InfraException(ErrorCodes.appError(error), message);
    }

    /**
     * Specific error.
     */
    public InfraException specificError(int error, String message, Throwable throwable) {
        return new InfraException(ErrorCodes.appError(error), message, throwable);
    }

    /**
     * Specific error.
     */
    public InfraException specificError(int error, Throwable throwable) {
        return new InfraException(ErrorCodes.appError(error), throwable);
    }

    /**
     * Unspecific error.
     */
    public InfraException unspecificError() {
        return new InfraException(UNSPECIFIC_ERROR);
    }

    /**
     * Unspecific error.
     */
    public InfraException unspecificError(String message) {
        return new InfraException(UNSPECIFIC_ERROR, message);
    }

    /**
     * Unspecific error.
     */
    public InfraException unspecificError(Throwable throwable) {
        return new InfraException(UNSPECIFIC_ERROR, throwable);
    }
}
