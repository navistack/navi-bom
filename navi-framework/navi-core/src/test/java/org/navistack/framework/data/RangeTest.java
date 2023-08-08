package org.navistack.framework.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RangeTest {
    @Test
    void constructor_shouldConstructWhenBothAreNulls() {
        Range<Integer> range = new Range<>(null, null);
        assertThat(range.getLeft())
                .isEqualTo(null);
        assertThat(range.getRight())
                .isEqualTo(null);
    }

    @Test
    void constructor_shouldConstructWhenLeftIsNull() {
        Range<Integer> range = new Range<>(null, 2);
        assertThat(range.getLeft())
                .isEqualTo(null);
        assertThat(range.getRight())
                .isEqualTo(2);
    }

    @Test
    void constructor_shouldConstructWhenRightIsNull() {
        Range<Integer> range = new Range<>(1, null);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(null);
    }

    @Test
    void constructor_shouldNotSwapWhenLeftIsLessThanRight() {
        Range<Integer> range = new Range<>(1, 2);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
    }

    @Test
    void constructor_shouldSwapWhenLeftIsGreaterThanRight() {
        Range<Integer> range = new Range<>(2, 1);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
    }

    @Test
    void withLeft_shouldConstructNewInstance() {
        Range<Integer> range = new Range<>(1, 2);
        Range<Integer> newRange = range.withLeft(3);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
        assertThat(newRange)
                .isNotSameAs(range);
        assertThat(newRange.getLeft())
                .isEqualTo(2);
        assertThat(newRange.getRight())
                .isEqualTo(3);
    }

    @Test
    void withLeft_shouldConstructNewInstanceWhenLeftIsNull() {
        Range<Integer> range = new Range<>(1, 2);
        Range<Integer> newRange = range.withLeft(null);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
        assertThat(newRange)
                .isNotSameAs(range);
        assertThat(newRange.getLeft())
                .isEqualTo(null);
        assertThat(newRange.getRight())
                .isEqualTo(2);
    }

    @Test
    void withRight_shouldConstructNewInstance() {
        Range<Integer> range = new Range<>(1, 2);
        Range<Integer> newRange = range.withRight(0);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
        assertThat(newRange)
                .isNotSameAs(range);
        assertThat(newRange.getLeft())
                .isEqualTo(0);
        assertThat(newRange.getRight())
                .isEqualTo(1);
    }

    @Test
    void withRight_shouldConstructNewInstanceWhenLeftIsNull() {
        Range<Integer> range = new Range<>(1, 2);
        Range<Integer> newRange = range.withRight(null);
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
        assertThat(newRange)
                .isNotSameAs(range);
        assertThat(newRange.getLeft())
                .isEqualTo(1);
        assertThat(newRange.getRight())
                .isEqualTo(null);
    }

    @Test
    void equals_shouldEqualsWhenLeftAndRightEquals() {
        Range<Integer> r1 = new Range<>(1, 2);
        Range<Integer> r2 = new Range<>(2, 1);
        assertThat(r1)
                .isEqualTo(r2);
    }

    @Test
    void equals_shouldNotEqualsWhenLeftAndRightNotEquals() {
        Range<Integer> r1 = new Range<>(1, 2);
        Range<Integer> r2 = new Range<>(1, 3);
        assertThat(r1)
                .isNotEqualTo(r2);
    }

    @Test
    void of() {
        Range<Integer> range = Range.of(1, 2);
        assertThat(range)
                .isNotNull();
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isEqualTo(2);
    }

    @Test
    void leftUnbounded() {
        Range<Integer> range = Range.leftUnbounded(2);
        assertThat(range)
                .isNotNull();
        assertThat(range.getLeft())
                .isNull();
        assertThat(range.getRight())
                .isEqualTo(2);
    }

    @Test
    void rightUnbounded() {
        Range<Integer> range = Range.rightUnbounded(1);
        assertThat(range)
                .isNotNull();
        assertThat(range.getLeft())
                .isEqualTo(1);
        assertThat(range.getRight())
                .isNull();
    }

    @Test
    void unbounded() {
        Range<Integer> range = Range.unbounded();
        assertThat(range)
                .isNotNull();
        assertThat(range.getLeft())
                .isNull();
        assertThat(range.getRight())
                .isNull();
    }

    @Test
    void overlaps_shouldBeFalseWhenOtherIsNull() {
        Range<Integer> r1 = new Range<>(null, null);
        Range<Integer> r2 = null;
        assertThat(r1.overlaps(r2))
                .isFalse();
    }

    @Test
    void overlaps_whenR1IsUnboundedAndR2IsUnbounded() {
        Range<Integer> r1 = new Range<>(null, null);
        Range<Integer> r2 = new Range<>(null, null);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsUnboundedAndR2IsLeftUnbounded() {
        Range<Integer> r1 = new Range<>(null, null);
        Range<Integer> r2 = new Range<>(null, 20);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsUnboundedAndR2IsRightUnbounded() {
        Range<Integer> r1 = new Range<>(null, null);
        Range<Integer> r2 = new Range<>(10, null);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsUnboundedAndR2IsBounded() {
        Range<Integer> r1 = new Range<>(null, null);
        Range<Integer> r2 = new Range<>(10, 20);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsLeftUnboundedAndR2IsUnbounded() {
        Range<Integer> r1 = new Range<>(null, 20);
        Range<Integer> r2 = new Range<>(null, null);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsLeftUnboundedAndR2IsLeftUnbounded() {
        Range<Integer> r1 = new Range<>(null, 20);
        Range<Integer> r2 = new Range<>(null, 10);
        assertThat(r1.overlaps(r2))
                .isTrue();

        Range<Integer> r3 = new Range<>(null, 20);
        Range<Integer> r4 = new Range<>(null, 20);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(null, 20);
        Range<Integer> r6 = new Range<>(null, 30);
        assertThat(r5.overlaps(r6))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsLeftUnboundedAndR2IsRightUnbounded() {
        Range<Integer> r1 = new Range<>(null, 20);
        Range<Integer> r2 = new Range<>(10, null);
        assertThat(r1.overlaps(r2))
                .isTrue();

        Range<Integer> r3 = new Range<>(null, 20);
        Range<Integer> r4 = new Range<>(20, null);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(null, 20);
        Range<Integer> r6 = new Range<>(30, null);
        assertThat(r5.overlaps(r6))
                .isFalse();
    }

    @Test
    void overlaps_whenR1IsLeftUnboundedAndR2IsBounded() {
        Range<Integer> r1 = new Range<>(null, 20);
        Range<Integer> r2 = new Range<>(0, 10);
        assertThat(r1.overlaps(r2))
                .isTrue();

        Range<Integer> r3 = new Range<>(null, 20);
        Range<Integer> r4 = new Range<>(10, 20);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(null, 20);
        Range<Integer> r6 = new Range<>(10, 30);
        assertThat(r5.overlaps(r6))
                .isTrue();

        Range<Integer> r7 = new Range<>(null, 20);
        Range<Integer> r8 = new Range<>(20, 30);
        assertThat(r7.overlaps(r8))
                .isTrue();

        Range<Integer> r9 = new Range<>(null, 20);
        Range<Integer> r10 = new Range<>(30, 40);
        assertThat(r9.overlaps(r10))
                .isFalse();
    }

    @Test
    void overlaps_whenR1IsRightUnboundedAndR2IsUnbounded() {
        Range<Integer> r1 = new Range<>(20, null);
        Range<Integer> r2 = new Range<>(null, null);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsRightUnboundedAndR2IsLeftUnbounded() {
        Range<Integer> r1 = new Range<>(20, null);
        Range<Integer> r2 = new Range<>(null, 10);
        assertThat(r1.overlaps(r2))
                .isFalse();

        Range<Integer> r3 = new Range<>(20, null);
        Range<Integer> r4 = new Range<>(null, 20);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(20, null);
        Range<Integer> r6 = new Range<>(null, 30);
        assertThat(r5.overlaps(r6))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsRightUnboundedAndR2IsRightUnbounded() {
        Range<Integer> r1 = new Range<>(20, null);
        Range<Integer> r2 = new Range<>(10, null);
        assertThat(r1.overlaps(r2))
                .isTrue();

        Range<Integer> r3 = new Range<>(20, null);
        Range<Integer> r4 = new Range<>(20, null);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(20, null);
        Range<Integer> r6 = new Range<>(30, null);
        assertThat(r5.overlaps(r6))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsRightUnboundedAndR2IsBounded() {
        Range<Integer> r1 = new Range<>(20, null);
        Range<Integer> r2 = new Range<>(0, 10);
        assertThat(r1.overlaps(r2))
                .isFalse();

        Range<Integer> r3 = new Range<>(20, null);
        Range<Integer> r4 = new Range<>(10, 20);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(20, null);
        Range<Integer> r6 = new Range<>(10, 30);
        assertThat(r5.overlaps(r6))
                .isTrue();

        Range<Integer> r7 = new Range<>(20, null);
        Range<Integer> r8 = new Range<>(20, 30);
        assertThat(r7.overlaps(r8))
                .isTrue();

        Range<Integer> r9 = new Range<>(20, null);
        Range<Integer> r10 = new Range<>(30, 40);
        assertThat(r9.overlaps(r10))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsBoundedAndR2IsUnbounded() {
        Range<Integer> r1 = new Range<>(20, 30);
        Range<Integer> r2 = new Range<>(null, null);
        assertThat(r1.overlaps(r2))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsBoundedAndR2IsLeftUnbounded() {
        Range<Integer> r1 = new Range<>(20, 30);
        Range<Integer> r2 = new Range<>(null, 10);
        assertThat(r1.overlaps(r2))
                .isFalse();

        Range<Integer> r3 = new Range<>(20, 30);
        Range<Integer> r4 = new Range<>(null, 20);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(20, 30);
        Range<Integer> r6 = new Range<>(null, 30);
        assertThat(r5.overlaps(r6))
                .isTrue();

        Range<Integer> r7 = new Range<>(20, 30);
        Range<Integer> r8 = new Range<>(null, 40);
        assertThat(r7.overlaps(r8))
                .isTrue();
    }

    @Test
    void overlaps_whenR1IsBoundedAndR2IsRightUnbounded() {
        Range<Integer> r1 = new Range<>(20, 30);
        Range<Integer> r2 = new Range<>(10, null);
        assertThat(r1.overlaps(r2))
                .isTrue();

        Range<Integer> r3 = new Range<>(20, 30);
        Range<Integer> r4 = new Range<>(20, null);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(20, 30);
        Range<Integer> r6 = new Range<>(30, null);
        assertThat(r5.overlaps(r6))
                .isTrue();

        Range<Integer> r7 = new Range<>(20, 30);
        Range<Integer> r8 = new Range<>(40, null);
        assertThat(r7.overlaps(r8))
                .isFalse();
    }

    @Test
    void overlaps_whenR1IsBoundedAndR2IsBounded() {
        Range<Integer> r1 = new Range<>(20, 30);
        Range<Integer> r2 = new Range<>(0, 10);
        assertThat(r1.overlaps(r2))
                .isFalse();

        Range<Integer> r3 = new Range<>(20, 30);
        Range<Integer> r4 = new Range<>(10, 20);
        assertThat(r3.overlaps(r4))
                .isTrue();

        Range<Integer> r5 = new Range<>(20, 30);
        Range<Integer> r6 = new Range<>(20, 25);
        assertThat(r5.overlaps(r6))
                .isTrue();

        Range<Integer> r7 = new Range<>(20, 30);
        Range<Integer> r8 = new Range<>(23, 28);
        assertThat(r7.overlaps(r8))
                .isTrue();

        Range<Integer> r9 = new Range<>(20, 30);
        Range<Integer> r10 = new Range<>(25, 30);
        assertThat(r9.overlaps(r10))
                .isTrue();

        Range<Integer> r11 = new Range<>(20, 30);
        Range<Integer> r12 = new Range<>(30, 40);
        assertThat(r11.overlaps(r12))
                .isTrue();

        Range<Integer> r13 = new Range<>(20, 30);
        Range<Integer> r14 = new Range<>(40, 50);
        assertThat(r13.overlaps(r14))
                .isFalse();
    }
}
