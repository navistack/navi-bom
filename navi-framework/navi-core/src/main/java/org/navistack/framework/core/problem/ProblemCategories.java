package org.navistack.framework.core.problem;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProblemCategories {
    /**
     * Reserved for use by platform.
     */
    public final int PLATFORM_PROBLEM = 0x0;

    /**
     * This category indicates problems caused by current system.
     */
    public final int SYSTEM_PROBLEM = 0x1;

    /**
     * This category indicates problems of specified domain.
     */
    public final int DOMAIN_PROBLEM = 0x2;

    /**
     * This category indicates problems due to external resource failure.
     */
    public final int EXTERNAL_PROBLEM = 0x3;

    /**
     * This category doesn't indicate actual problems,
     * but inappropriate calls from clients.
     */
    public final int CLIENT_PROBLEM = 0x4;

    /**
     * This category indicates problems that are hard or unable to be categorized.
     */
    public final int UNCATEGORIZED_PROBLEM = 0xF;
}
