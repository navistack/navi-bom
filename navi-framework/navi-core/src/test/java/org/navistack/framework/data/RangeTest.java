package org.navistack.framework.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RangeTest {
    @Test
    public void rangeNullNull() {
        Range<Integer> range = new Range<>(null, null);
        assertThat(range.getLeft()).isEqualTo(null);
        assertThat(range.getRight()).isEqualTo(null);
    }

    @Test
    public void rangeNullNonnull() {
        Range<Integer> range = new Range<>(null, 2);
        assertThat(range.getLeft()).isEqualTo(null);
        assertThat(range.getRight()).isEqualTo(2);
    }

    @Test
    public void rangeNonnullNull() {
        Range<Integer> range = new Range<>(1, null);
        assertThat(range.getLeft()).isEqualTo(1);
        assertThat(range.getRight()).isEqualTo(null);
    }

    @Test
    public void rangeLittleBig() {
        Range<Integer> range = new Range<>(1, 2);
        assertThat(range.getLeft()).isEqualTo(1);
        assertThat(range.getRight()).isEqualTo(2);
    }

    @Test
    public void rangeBigLittle() {
        Range<Integer> range = new Range<>(2, 1);
        assertThat(range.getLeft()).isEqualTo(1);
        assertThat(range.getRight()).isEqualTo(2);
    }

    @Test
    public void setLeft() {
        Range<Integer> range = new Range<>(1, 2);
        range.setLeft(3);
        assertThat(range.getLeft()).isEqualTo(2);
        assertThat(range.getRight()).isEqualTo(3);
    }

    @Test
    public void setLeftNull() {
        Range<Integer> range = new Range<>(1, 2);
        range.setLeft(null);
        assertThat(range.getLeft()).isEqualTo(null);
        assertThat(range.getRight()).isEqualTo(2);
    }

    @Test
    public void setRight() {
        Range<Integer> range = new Range<>(1, 2);
        range.setRight(0);
        assertThat(range.getLeft()).isEqualTo(0);
        assertThat(range.getRight()).isEqualTo(1);
    }

    @Test
    public void setRightNull() {
        Range<Integer> range = new Range<>(1, 2);
        range.setRight(null);
        assertThat(range.getLeft()).isEqualTo(1);
        assertThat(range.getRight()).isEqualTo(null);
    }
}
