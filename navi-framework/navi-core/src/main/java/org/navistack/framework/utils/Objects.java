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

    public <T> void requireNull(T o) {
        if (o != null) {
            throw new IllegalArgumentException();
        }
    }

    public <T> void requireNull(T o, String message) {
        if (o != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public <T> T requireNonNull(T o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return o;
    }

    public <T> T requireNonNull(T o, String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }
}
