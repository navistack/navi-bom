package org.navistack.framework.captcha;

import org.navistack.framework.core.problem.ThrowableProblem;

public class CaptchaTestFailureProblem extends ThrowableProblem {
    private final static String ERROR = "CaptchaTestFailure";
    private final static String MESSAGE = "CAPTCHA testing failed";

    public CaptchaTestFailureProblem() {
        super(ERROR, MESSAGE);
    }
}
