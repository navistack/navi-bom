package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.function.IntFunction;

@UtilityClass
public class Arrays {
    /**
     * Get element from array null-safely
     * @param arr array
     * @param idx index of element
     * @return element at the index of array or null if index out of bound
     */
    public <T> T get(T[] arr, int idx) {
        return idx < 0 || arr == null || arr.length <= idx ? null : arr[idx];
    }

    /**
     * Check if array is empty
     * @param arr array
     * @return true if array is empty (length == 0) or false if array is null
     */
    public <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * Check if array is not empty
     * @param arr array
     * @return true if array is not null and not empty (length > 0)
     */
    public <T> boolean isNotEmpty(T[] arr) {
        return arr != null && arr.length > 0;
    }

    /**
     * Shift elements in array right
     * @param arr array
     * @param length length to shift
     * @param generator a function which produces a new array of the desired type and the provided length
     */
    public <T> T[] shift(T[] arr, int length, IntFunction<T[]> generator) {
        if (arr == null) {
            throw new NullPointerException("arr must not be null");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("length must not be negative or zero");
        }
        if (arr.length <= 0) {
            return generator.apply(0);
        }
        T[] newArray = generator.apply(arr.length + length);
        System.arraycopy(arr, 0, newArray, length, arr.length);
        return newArray;
    }

    /**
     * return arguments as is
     * @param elems elements of array
     * @return array contains elems
     */
    @SafeVarargs
    public <T> T[] asArray(T...elems) {
        return elems;
    }
}
