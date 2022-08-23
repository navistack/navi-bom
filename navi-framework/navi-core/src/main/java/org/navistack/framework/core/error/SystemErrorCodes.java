package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SystemErrorCodes {
    public final int GENERAL_ERROR = ErrorCodeBuilder.systemError(0x00);
}
