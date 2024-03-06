package org.navistack.framework.core.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodes {
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

    public int appError(int error) {
        return error(ErrorCodeCategories.APP_ERROR, error);
    }

    public int infraError(int error) {
        return error(ErrorCodeCategories.INFRA_ERROR, error);
    }

    public int userError(int error) {
        return error(ErrorCodeCategories.USER_ERROR, error);
    }

    public int globalAppError(int error) {
        return error(ErrorCodeCategories.GLOBAL_APP_ERROR, error);
    }

    public int globalInfraError(int error) {
        return error(ErrorCodeCategories.GLOBAL_INFRA_ERROR, error);
    }

    public int globalUserError(int error) {
        return error(ErrorCodeCategories.GLOBAL_USER_ERROR, error);
    }
}
