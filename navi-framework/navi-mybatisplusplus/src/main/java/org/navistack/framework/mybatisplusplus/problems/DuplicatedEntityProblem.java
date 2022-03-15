package org.navistack.framework.mybatisplusplus.problems;

import org.navistack.framework.core.problem.ThrowableBizProblem;

public class DuplicatedEntityProblem extends ThrowableBizProblem {
    private static final String ERROR_CODE = "EntityDuplicated";

    public DuplicatedEntityProblem() {
        super(ERROR_CODE);
    }

    public DuplicatedEntityProblem(String message) {
        super(ERROR_CODE, message);
    }

    public DuplicatedEntityProblem(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public DuplicatedEntityProblem(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public DuplicatedEntityProblem(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ERROR_CODE, message, cause, enableSuppression, writableStackTrace);
    }
}
