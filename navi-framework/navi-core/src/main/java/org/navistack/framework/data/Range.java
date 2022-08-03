package org.navistack.framework.data;

import lombok.Data;

@Data
public class Range<T extends Comparable<? super T>> {
    private T left;
    private T right;

    public Range(T left, T right) {
        this.left = left;
        this.right = right;
        compareAndSwap();
    }

    public void setLeft(T left) {
        this.left = left;
        compareAndSwap();
    }

    public void setRight(T right) {
        this.right = right;
        compareAndSwap();
    }

    private void compareAndSwap() {
        if (left == null || right == null) {
            return;
        }

        if (left.compareTo(right) > 0) {
            T t = left;
            left = right;
            right = t;
        }
    }
}
