package org.navistack.framework.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Range<T extends Comparable<? super T>> {
    private final T left;
    private final T right;

    public Range(T left, T right) {
        if (left != null && right != null && left.compareTo(right) > 0) {
            T t = left;
            left = right;
            right = t;
        }
        this.left = left;
        this.right = right;
    }

    public Range<T> withLeft(T left) {
        return new Range<>(left, right);
    }

    public Range<T> withRight(T right) {
        return new Range<>(left, right);
    }

    public boolean overlaps(Range<T> other) {
        if (other == null) {
            return false;
        }

        T left = other.getLeft();
        T right = other.getRight();

        if (this.left == null && this.right == null) {
            return true;
        }

        if (left == null && right == null) {
            return true;
        }

        if (this.left == null && left == null) {
            return true;
        }

        if (this.right == null && right == null) {
            return true;
        }

        if (this.left == null || right == null) {
            return this.right.compareTo(left) >= 0;
        }

        if (this.right == null || left == null) {
            return this.left.compareTo(right) <= 0;
        }

        return this.left.compareTo(right) <= 0
                && this.right.compareTo(left) >= 0;
    }

    public static <T extends Comparable<? super T>> Range<T> of(T left, T right) {
        return new Range<>(left, right);
    }

    public static <T extends Comparable<? super T>> Range<T> leftUnbounded(T right) {
        return new Range<>(null, right);
    }

    public static <T extends Comparable<? super T>> Range<T> rightUnbounded(T left) {
        return new Range<>(left, null);
    }

    public static <T extends Comparable<? super T>> Range<T> unbounded() {
        return new Range<>(null, null);
    }
}
