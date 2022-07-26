package org.navistack.framework.data;

import lombok.Data;

@Data
public class Range<T extends Comparable<? super T>> {
    private T left;
    private T right;

    public Range(T left, T right) {
        if (left != null && right != null) {
            if (left.compareTo(right) > 0) {
                T tmp = left;
                left = right;
                right = tmp;
            }
        }
        this.left = left;
        this.right = right;
    }
}
