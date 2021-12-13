package org.navistack.framework.captcha.afs;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AfsResponseCodes {
    final int VALIDATION_PASSED = 100;
    final int VALIDATION_FAILED = 900;
    final int INVALID_PARAMETERS = 400;
    final int SERVER_ERROR = 500;
}
