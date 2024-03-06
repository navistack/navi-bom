package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodeCategories {
    /**
     * User failure.
     */
    public final int USER_ERROR = 0x1;

    /**
     * System failure.
     */
    public final int APP_ERROR = 0x2;

    /**
     * Infrastructure failure.
     */
    public final int INFRA_ERROR = 0x3;

    /**
     * Global user failure.
     */
    public final int GLOBAL_USER_ERROR = 0x9;

    /**
     * Global system failure.
     */
    public final int GLOBAL_APP_ERROR = 0xA;

    /**
     * Global infrastructure failure.
     */
    public final int GLOBAL_INFRA_ERROR = 0xB;
}
