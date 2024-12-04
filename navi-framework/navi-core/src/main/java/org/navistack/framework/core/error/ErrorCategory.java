package org.navistack.framework.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum ErrorCategory {
    User(0x1),
    Domain(0x2),
    Infra(0x3);

    private static final int ERROR_BITS = 3 * 4;
    private static final int ERROR_MASK = ~(-1 << ERROR_BITS);
    private static final int CATEGORY_BITS = 4;
    private static final int CATEGORY_MASK = ~(-1 << CATEGORY_BITS);

    private final int code;

    public int errorCode(int subCode) {
        return (code & CATEGORY_MASK) << CATEGORY_BITS
                | (subCode & ERROR_MASK)
                ;
    }
}
