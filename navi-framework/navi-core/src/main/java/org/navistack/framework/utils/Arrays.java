package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Arrays {
    public <T> T get(T[] arr, int idx) {
        return idx < 0 || arr == null || arr.length <= idx ? null : arr[idx];
    }

    public <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public <T> boolean isNotEmpty(T[] arr) {
        return arr != null && arr.length > 0;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] shift(T[] arr, int length) {
        if (arr == null) {
            throw new NullPointerException("arr must not be null");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("length must not be negative or zero");
        }
        if (arr.length <= 0) {
            return (T[]) new Object[]{};
        }
        T[] newArray = (T[]) new Object[arr.length + length];
        System.arraycopy(arr, 0, newArray, length, arr.length);
        return newArray;
    }
}
