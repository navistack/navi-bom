package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Objects {
    public <T> boolean isNull(T o) {
        return o == null;
    }

    public <T> boolean isNotNull(T o) {
        return o != null;
    }

    public <T> boolean equals(T left, T right) {
        return left != null && left.equals(right);
    }
}
