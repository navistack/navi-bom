package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PlatformProblems {
    public final int GENERIC_FAILURE = ProblemCodeBuilder.platformProblem(0x000);

    /**
     * Authentication failed, for example invalid credentials.
     */
    public final int AUTHENTICATION_FAILURE = ProblemCodeBuilder.platformProblem(0x001);

    /**
     * No permission, unauthorized access, etc.
     */
    public final int AUTHORIZATION_FAILURE = ProblemCodeBuilder.platformProblem(0x002);

    /**
     * Invalid parameters received,
     * like missing parameter, mismatched data type, illegal values, etc.
     */
    public final int INVALID_PARAMETER = ProblemCodeBuilder.platformProblem(0x003);

    /**
     * Parameter missing
     */
    public final int MISSING_PARAMETER = ProblemCodeBuilder.platformProblem(0x004);

    /**
     * User fails to pass CAPTCHA test
     */
    public final int CAPTCHA_TEST_FAILED = ProblemCodeBuilder.platformProblem(0x005);

    /**
     * Unknown endpoint
     */
    public final int UNKNOWN_ENDPOINT = ProblemCodeBuilder.platformProblem(0x006);

    /**
     * Resource locked
     */
    public final int RESOURCE_LOCKED = ProblemCodeBuilder.platformProblem(0x007);

    public PlatformProblem authenticationFailure(String message) {
        return new PlatformProblem(AUTHENTICATION_FAILURE, message);
    }

    public PlatformProblem authorizationFailure(String message) {
        return new PlatformProblem(AUTHORIZATION_FAILURE, message);
    }

    public PlatformProblem parameterInvalid(String message) {
        return new PlatformProblem(INVALID_PARAMETER, message);
    }

    public PlatformProblem captchaTestFailed(String message) {
        return new PlatformProblem(CAPTCHA_TEST_FAILED, message);
    }

    public PlatformProblem resourceLocked(String message) {
        return new PlatformProblem(RESOURCE_LOCKED, message);
    }
}
