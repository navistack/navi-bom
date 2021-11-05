package org.navistack.framework.core.problem;

public class ThrowableInfraProblem extends ThrowableProblem {
    public ThrowableInfraProblem(String error) {
        super(error);
    }

    public ThrowableInfraProblem(String error, String message) {
        super(error, message);
    }

    public ThrowableInfraProblem(String error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public ThrowableInfraProblem(String error, Throwable cause) {
        super(error, cause);
    }

    public ThrowableInfraProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
