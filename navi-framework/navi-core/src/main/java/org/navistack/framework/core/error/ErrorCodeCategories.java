package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodeCategories {
    /**
     * System failure.
     */
    public final int SYSTEM_ERROR = 0x1;

    /**
     * Error occurred in domain.
     */
    public final int DOMAIN_ERROR = 0x2;

    /**
     * External resource failure.
     */
    public final int EXTERNAL_ERROR = 0x3;

    /**
     * User input error / user call error.
     */
    public final int USER_ERROR = 0x4;

    /**
     * Uncategorized error / unknown error.
     */
    public final int UNCATEGORIZED_ERROR = 0xF;
}
