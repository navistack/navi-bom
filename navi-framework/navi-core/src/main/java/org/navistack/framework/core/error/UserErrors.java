package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserErrors {
    /**
     * Unspecific error.
     */
    public final int UNSPECIFIC_ERROR = ErrorCodes.globalUserError(0x00);

    /**
     * Authentication failed, for example invalid credentials.
     */
    public final int AUTHENTICATION_FAILURE = ErrorCodes.globalUserError(0x001);

    /**
     * No permission, unauthorized access, etc.
     */
    public final int AUTHORIZATION_FAILURE = ErrorCodes.globalUserError(0x002);

    /**
     * Invalid parameters received.
     * Like missing parameter, mismatched data type, illegal values, etc.
     */
    public final int INVALID_PARAMETER = ErrorCodes.globalUserError(0x003);

    /**
     * Parameter missing.
     */
    public final int MISSING_PARAMETER = ErrorCodes.globalUserError(0x004);

    /**
     * User fails to pass CAPTCHA test.
     */
    public final int CAPTCHA_TEST_FAILED = ErrorCodes.globalUserError(0x005);

    /**
     * Unknown endpoint.
     */
    public final int UNKNOWN_ENDPOINT = ErrorCodes.globalUserError(0x006);

    /**
     * Resource locked.
     */
    public final int RESOURCE_LOCKED = ErrorCodes.globalUserError(0x007);

    /**
     * Illegal request.
     */
    public final int ILLEGAL_REQUEST = ErrorCodes.globalUserError(0x008);

    /**
     * Frequent request.
     */
    public final int FREQUENT_REQUEST = ErrorCodes.globalUserError(0x009);

    /**
     * Specific error.
     */
    public UserException specificError(int error) {
        return new UserException(ErrorCodes.appError(error));
    }

    /**
     * Specific error.
     */
    public UserException specificError(int error, String message) {
        return new UserException(ErrorCodes.appError(error), message);
    }

    /**
     * Specific error.
     */
    public UserException specificError(int error, String message, Throwable throwable) {
        return new UserException(ErrorCodes.appError(error), message, throwable);
    }

    /**
     * Specific error.
     */
    public UserException specificError(int error, Throwable throwable) {
        return new UserException(ErrorCodes.appError(error), throwable);
    }

    /**
     * Unspecific error.
     */
    public UserException unspecificError() {
        return new UserException(UNSPECIFIC_ERROR);
    }

    /**
     * Unspecific error.
     */
    public UserException unspecificError(String message) {
        return new UserException(UNSPECIFIC_ERROR, message);
    }

    /**
     * Unspecific error.
     */
    public UserException unspecificError(Throwable throwable) {
        return new UserException(UNSPECIFIC_ERROR, throwable);
    }

    /**
     * Unspecific error.
     */
    public UserException unspecificError(String message, Throwable throwable) {
        return new UserException(UNSPECIFIC_ERROR, message, throwable);
    }

    /**
     * Authentication failed, for example invalid credentials.
     */
    public UserException authenticationFailure() {
        return new UserException(AUTHENTICATION_FAILURE);
    }

    /**
     * Authentication failed, for example invalid credentials.
     */
    public UserException authenticationFailure(String message) {
        return new UserException(AUTHENTICATION_FAILURE, message);
    }

    /**
     * Authentication failed, for example invalid credentials.
     */
    public UserException authenticationFailure(Throwable throwable) {
        return new UserException(AUTHENTICATION_FAILURE, throwable);
    }

    /**
     * Authentication failed, for example invalid credentials.
     */
    public UserException authenticationFailure(String message, Throwable throwable) {
        return new UserException(AUTHENTICATION_FAILURE, message, throwable);
    }

    /**
     * No permission, unauthorized access, etc.
     */
    public UserException authorizationFailure() {
        return new UserException(AUTHORIZATION_FAILURE);
    }

    /**
     * No permission, unauthorized access, etc.
     */
    public UserException authorizationFailure(String message) {
        return new UserException(AUTHORIZATION_FAILURE, message);
    }

    /**
     * No permission, unauthorized access, etc.
     */
    public UserException authorizationFailure(Throwable throwable) {
        return new UserException(AUTHORIZATION_FAILURE, throwable);
    }

    /**
     * No permission, unauthorized access, etc.
     */
    public UserException authorizationFailure(String message, Throwable throwable) {
        return new UserException(AUTHORIZATION_FAILURE, message, throwable);
    }

    /**
     * Invalid parameters received.
     * Like missing parameter, mismatched data type, illegal values, etc.
     */
    public UserException invalidParameter() {
        return new UserException(INVALID_PARAMETER);
    }

    /**
     * Invalid parameters received.
     * Like missing parameter, mismatched data type, illegal values, etc.
     */
    public UserException invalidParameter(String message) {
        return new UserException(INVALID_PARAMETER, message);
    }

    /**
     * Invalid parameters received.
     * Like missing parameter, mismatched data type, illegal values, etc.
     */
    public UserException invalidParameter(Throwable throwable) {
        return new UserException(INVALID_PARAMETER, throwable);
    }

    /**
     * Invalid parameters received.
     * Like missing parameter, mismatched data type, illegal values, etc.
     */
    public UserException invalidParameter(String message, Throwable throwable) {
        return new UserException(INVALID_PARAMETER, message, throwable);
    }

    /**
     * Parameter missing.
     */
    public UserException missingParameter() {
        return new UserException(MISSING_PARAMETER);
    }

    /**
     * Parameter missing.
     */
    public UserException missingParameter(String message) {
        return new UserException(MISSING_PARAMETER, message);
    }

    /**
     * Parameter missing.
     */
    public UserException missingParameter(Throwable throwable) {
        return new UserException(MISSING_PARAMETER, throwable);
    }

    /**
     * Parameter missing.
     */
    public UserException missingParameter(String message, Throwable throwable) {
        return new UserException(MISSING_PARAMETER, message, throwable);
    }

    /**
     * User fails to pass CAPTCHA test.
     */
    public UserException captchaTestFailed() {
        return new UserException(CAPTCHA_TEST_FAILED);
    }

    /**
     * User fails to pass CAPTCHA test.
     */
    public UserException captchaTestFailed(String message) {
        return new UserException(CAPTCHA_TEST_FAILED, message);
    }

    /**
     * User fails to pass CAPTCHA test.
     */
    public UserException captchaTestFailed(Throwable throwable) {
        return new UserException(CAPTCHA_TEST_FAILED, throwable);
    }

    /**
     * User fails to pass CAPTCHA test.
     */
    public UserException captchaTestFailed(String message, Throwable throwable) {
        return new UserException(CAPTCHA_TEST_FAILED, message, throwable);
    }

    /**
     * Unknown endpoint.
     */
    public UserException unknownEndpoint() {
        return new UserException(UNKNOWN_ENDPOINT);
    }

    /**
     * Unknown endpoint.
     */
    public UserException unknownEndpoint(String message) {
        return new UserException(UNKNOWN_ENDPOINT, message);
    }

    /**
     * Unknown endpoint.
     */
    public UserException unknownEndpoint(Throwable throwable) {
        return new UserException(UNKNOWN_ENDPOINT, throwable);
    }

    /**
     * Unknown endpoint.
     */
    public UserException unknownEndpoint(String message, Throwable throwable) {
        return new UserException(UNKNOWN_ENDPOINT, message, throwable);
    }

    /**
     * Resource locked.
     */
    public UserException resourceLocked() {
        return new UserException(RESOURCE_LOCKED);
    }

    /**
     * Resource locked.
     */
    public UserException resourceLocked(String message) {
        return new UserException(RESOURCE_LOCKED, message);
    }

    /**
     * Resource locked.
     */
    public UserException resourceLocked(Throwable throwable) {
        return new UserException(RESOURCE_LOCKED, throwable);
    }

    /**
     * Resource locked.
     */
    public UserException resourceLocked(String message, Throwable throwable) {
        return new UserException(RESOURCE_LOCKED, message, throwable);
    }

    /**
     * Illegal request.
     */
    public UserException illegalRequest() {
        return new UserException(ILLEGAL_REQUEST);
    }

    /**
     * Illegal request.
     */
    public UserException illegalRequest(String message) {
        return new UserException(ILLEGAL_REQUEST, message);
    }

    /**
     * Illegal request.
     */
    public UserException illegalRequest(Throwable throwable) {
        return new UserException(ILLEGAL_REQUEST, throwable);
    }

    /**
     * Illegal request.
     */
    public UserException illegalRequest(String message, Throwable throwable) {
        return new UserException(ILLEGAL_REQUEST, message, throwable);
    }

    /**
     * Frequent request.
     */
    public UserException frequentRequest() {
        return new UserException(FREQUENT_REQUEST);
    }

    /**
     * Frequent request.
     */
    public UserException frequentRequest(String message) {
        return new UserException(FREQUENT_REQUEST, message);
    }

    /**
     * Frequent request.
     */
    public UserException frequentRequest(Throwable throwable) {
        return new UserException(FREQUENT_REQUEST, throwable);
    }

    /**
     * Frequent request.
     */
    public UserException frequentRequest(String message, Throwable throwable) {
        return new UserException(FREQUENT_REQUEST, message, throwable);
    }
}
