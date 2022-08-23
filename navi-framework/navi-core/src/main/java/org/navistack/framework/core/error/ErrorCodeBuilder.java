package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodeBuilder {
    private final int ERROR_BITS = 3 * 4;
    private final int ERROR_MASK = ~(-1 << ERROR_BITS);

    private final int CATEGORY_BITS = 4;
    private final int CATEGORY_MASK = ~(-1 << CATEGORY_BITS);
    private final int CATEGORY_OFFSET = CATEGORY_BITS;

    public int nonError() {
        return 0x0000;
    }

    public int error(int category, int error) {
        return (category & CATEGORY_MASK) << CATEGORY_OFFSET
                | (error & ERROR_MASK)
                ;
    }

    public int systemError(int error) {
        return error(ErrorCodeCategories.SYSTEM_ERROR, error);
    }

    public int domainError(int error) {
        return error(ErrorCodeCategories.DOMAIN_ERROR, error);
    }

    public int externalError(int error) {
        return error(ErrorCodeCategories.EXTERNAL_ERROR, error);
    }

    public int userError(int error) {
        return error(ErrorCodeCategories.USER_ERROR, error);
    }

    public int uncategorizedError() {
        return error(ErrorCodeCategories.UNCATEGORIZED_ERROR, 0xFFF);
    }
}
