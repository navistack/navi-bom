package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

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
     */
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

    /**
     * Prepend elements to array.
     * @param arr array
     * @param elems elements to be prepended to array
     * @return new array combines array and elements
     */
    @SuppressWarnings("unchecked")
    public <T> T[] prepend(T[] arr, T... elems) {
        if (arr == null) {
            arr = (T[]) new Object[]{};
        }
        if (elems == null) {
            elems = (T[]) new Object[]{};
        }
        int newLength = arr.length + elems.length;
        if (newLength <= 0) {
            return (T[]) new Object[]{};
        }
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(elems, 0, newArray, 0, elems.length);
        System.arraycopy(arr, 0, newArray, elems.length, arr.length);
        return newArray;
    }

    /**
     * return arguments as is
     * @param elems elements of array
     * @return array contains elems
     */
    @SuppressWarnings("unchecked")
    public <T> T[] asArray(T...elems) {
        if (elems == null) {
            return (T[])new Object[]{};
        } else {
            return elems;
        }
    }
}
