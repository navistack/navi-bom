package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Booleans {
    public boolean isTrue(Boolean value) {
        return value != null && value;
    }

    public boolean isFalse(Boolean value) {
        return value != null && !value;
    }

    public boolean isNullOrFalse(Boolean value) {
        return value == null || !value;
    }
}
