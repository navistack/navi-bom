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

    @SafeVarargs
    public <T> T firstNonNull(T... objects) {
        if (objects == null || objects.length == 0) {
            return null;
        }

        for (T object : objects) {
            if (object != null) {
                return object;
            }
        }

        return null;
    }
}
