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
}
