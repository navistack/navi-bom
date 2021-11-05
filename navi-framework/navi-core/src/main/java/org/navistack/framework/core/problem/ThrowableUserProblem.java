package org.navistack.framework.core.problem;

public class ThrowableUserProblem extends ThrowableProblem {
    public ThrowableUserProblem(String error) {
        super(error);
    }

    public ThrowableUserProblem(String error, String message) {
        super(error, message);
    }

    public ThrowableUserProblem(String error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public ThrowableUserProblem(String error, Throwable cause) {
        super(error, cause);
    }

    public ThrowableUserProblem(String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
