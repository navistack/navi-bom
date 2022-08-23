package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExternalErrorCodes {
    public final int GENERAL_ERROR = ErrorCodeBuilder.externalError(0x00);
}
