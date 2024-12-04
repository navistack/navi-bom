package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodes {
    /**
     * Authentication failed, for example invalid credentials.
     */
    public final int AUTHENTICATION_FAILURE = ErrorCategory.User.errorCode(0x101);

    /**
     * No permission, unauthorized access, etc.
     */
    public final int AUTHORIZATION_FAILURE = ErrorCategory.User.errorCode(0x102);

    /**
     * Invalid parameters received.
     * Like missing parameter, mismatched data type, illegal values, etc.
     */
    public final int INVALID_PARAMETER = ErrorCategory.User.errorCode(0x103);

    /**
     * Parameter missing.
     */
    public final int MISSING_PARAMETER = ErrorCategory.User.errorCode(0x104);

    /**
     * User fails to pass CAPTCHA test.
     */
    public final int CAPTCHA_TEST_FAILED = ErrorCategory.User.errorCode(0x105);

    /**
     * Unknown endpoint.
     */
    public final int UNKNOWN_ENDPOINT = ErrorCategory.User.errorCode(0x106);

    /**
     * Resource locked.
     */
    public final int RESOURCE_LOCKED = ErrorCategory.User.errorCode(0x107);

    /**
     * Illegal request.
     */
    public final int ILLEGAL_REQUEST = ErrorCategory.User.errorCode(0x108);

    /**
     * Frequent request.
     */
    public final int FREQUENT_REQUEST = ErrorCategory.User.errorCode(0x109);
}
