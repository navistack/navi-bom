package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserErrorCodes {
    public final int GENERAL_ERROR = ErrorCodeBuilder.userError(0x00);

    /**
     * Authentication failed, for example invalid credentials.
     */
    public final int AUTHENTICATION_FAILURE = ErrorCodeBuilder.userError(0x001);

    /**
     * No permission, unauthorized access, etc.
     */
    public final int AUTHORIZATION_FAILURE = ErrorCodeBuilder.userError(0x002);

    /**
     * Invalid parameters received,
     * like missing parameter, mismatched data type, illegal values, etc.
     */
    public final int INVALID_PARAMETER = ErrorCodeBuilder.userError(0x003);

    /**
     * Parameter missing
     */
    public final int MISSING_PARAMETER = ErrorCodeBuilder.userError(0x004);

    /**
     * User fails to pass CAPTCHA test
     */
    public final int CAPTCHA_TEST_FAILED = ErrorCodeBuilder.userError(0x005);

    /**
     * Unknown endpoint
     */
    public final int UNKNOWN_ENDPOINT = ErrorCodeBuilder.userError(0x006);

    /**
     * Resource locked
     */
    public final int RESOURCE_LOCKED = ErrorCodeBuilder.userError(0x007);

    /**
     * Illegal request
     */
    public final int ILLEGAL_REQUEST = ErrorCodeBuilder.userError(0x008);

    /**
     * Frequent request
     */
    public final int FREQUENT_REQUEST = ErrorCodeBuilder.userError(0x009);
}
