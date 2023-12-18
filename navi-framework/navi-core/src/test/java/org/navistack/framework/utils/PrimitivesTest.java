package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrimitivesTest {
    @Test
    void testEq() {
        assertThat(Primitives.eq((byte) 0, (byte) 0))
                .isTrue();
        assertThat(Primitives.eq(null, (byte) 0))
                .isFalse();
        assertThat(Primitives.eq((byte) 0, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Byte) null))
                .isFalse();
        assertThat(Primitives.eq((short) 0, (short) 0))
                .isTrue();
        assertThat(Primitives.eq(null, (short) 0))
                .isFalse();
        assertThat(Primitives.eq((short) 0, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Short) null))
                .isFalse();
        assertThat(Primitives.eq(0, 0))
                .isTrue();
        assertThat(Primitives.eq(null, 0))
                .isFalse();
        assertThat(Primitives.eq(0, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Integer) null))
                .isFalse();
        assertThat(Primitives.eq((long) 0, (long) 0))
                .isTrue();
        assertThat(Primitives.eq(null, (long) 0))
                .isFalse();
        assertThat(Primitives.eq((long) 0, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Long) null))
                .isFalse();
        assertThat(Primitives.eq((float) 0.0, (float) 0.0))
                .isTrue();
        assertThat(Primitives.eq(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.eq((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Float) null))
                .isFalse();
        assertThat(Primitives.eq(0.0, 0.0))
                .isTrue();
        assertThat(Primitives.eq(null, 0.0))
                .isFalse();
        assertThat(Primitives.eq(0.0, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Double) null))
                .isFalse();
        assertThat(Primitives.eq(true, true))
                .isTrue();
        assertThat(Primitives.eq(false, false))
                .isTrue();
        assertThat(Primitives.eq(null, true))
                .isFalse();
        assertThat(Primitives.eq(true, null))
                .isFalse();
        assertThat(Primitives.eq(null, (Boolean) null))
                .isFalse();
    }

    @Test
    void testNeq() {
        assertThat(Primitives.neq((byte) 0, (byte) 0))
                .isFalse();
        assertThat(Primitives.neq(null, (byte) 0))
                .isTrue();
        assertThat(Primitives.neq((byte) 0, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Byte) null))
                .isTrue();
        assertThat(Primitives.neq((short) 0, (short) 0))
                .isFalse();
        assertThat(Primitives.neq(null, (short) 0))
                .isTrue();
        assertThat(Primitives.neq((short) 0, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Short) null))
                .isTrue();
        assertThat(Primitives.neq(0, 0))
                .isFalse();
        assertThat(Primitives.neq(null, 0))
                .isTrue();
        assertThat(Primitives.neq(0, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Integer) null))
                .isTrue();
        assertThat(Primitives.neq((long) 0, (long) 0))
                .isFalse();
        assertThat(Primitives.neq(null, (long) 0))
                .isTrue();
        assertThat(Primitives.neq((long) 0, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Long) null))
                .isTrue();
        assertThat(Primitives.neq((float) 0.0, (float) 0.0))
                .isFalse();
        assertThat(Primitives.neq(null, (float) 0.0))
                .isTrue();
        assertThat(Primitives.neq((float) 0.0, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Float) null))
                .isTrue();
        assertThat(Primitives.neq(0.0, 0.0))
                .isFalse();
        assertThat(Primitives.neq(null, 0.0))
                .isTrue();
        assertThat(Primitives.neq(0.0, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Double) null))
                .isTrue();
        assertThat(Primitives.neq(true, true))
                .isFalse();
        assertThat(Primitives.neq(false, false))
                .isFalse();
        assertThat(Primitives.neq(null, true))
                .isTrue();
        assertThat(Primitives.neq(true, null))
                .isTrue();
        assertThat(Primitives.neq(null, (Boolean) null))
                .isTrue();
    }

    @Test
    void testGt() {
        assertThat(Primitives.gt((byte) 1, (byte) 0))
                .isTrue();
        assertThat(Primitives.gt(null, (byte) 0))
                .isFalse();
        assertThat(Primitives.gt((byte) 0, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Byte) null))
                .isFalse();
        assertThat(Primitives.gt((short) 1, (short) 0))
                .isTrue();
        assertThat(Primitives.gt(null, (short) 0))
                .isFalse();
        assertThat(Primitives.gt((short) 0, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Short) null))
                .isFalse();
        assertThat(Primitives.gt(1, 0))
                .isTrue();
        assertThat(Primitives.gt(null, 0))
                .isFalse();
        assertThat(Primitives.gt(0, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Integer) null))
                .isFalse();
        assertThat(Primitives.gt((long) 1, (long) 0))
                .isTrue();
        assertThat(Primitives.gt(null, (long) 0))
                .isFalse();
        assertThat(Primitives.gt((long) 0, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Long) null))
                .isFalse();
        assertThat(Primitives.gt((float) 1.0, (float) 0.0))
                .isTrue();
        assertThat(Primitives.gt(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.gt((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Float) null))
                .isFalse();
        assertThat(Primitives.gt(1.0, 0.0))
                .isTrue();
        assertThat(Primitives.gt(null, 0.0))
                .isFalse();
        assertThat(Primitives.gt(0.0, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Double) null))
                .isFalse();
        assertThat(Primitives.gt(true, false))
                .isTrue();
        assertThat(Primitives.gt(false, false))
                .isFalse();
        assertThat(Primitives.gt(null, true))
                .isFalse();
        assertThat(Primitives.gt(true, null))
                .isFalse();
        assertThat(Primitives.gt(null, (Boolean) null))
                .isFalse();
    }

    @Test
    void testGe() {
        assertThat(Primitives.ge((byte) 1, (byte) 0))
                .isTrue();
        assertThat(Primitives.ge(null, (byte) 0))
                .isFalse();
        assertThat(Primitives.ge((byte) 0, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Byte) null))
                .isFalse();
        assertThat(Primitives.ge((short) 1, (short) 0))
                .isTrue();
        assertThat(Primitives.ge(null, (short) 0))
                .isFalse();
        assertThat(Primitives.ge((short) 0, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Short) null))
                .isFalse();
        assertThat(Primitives.ge(1, 0))
                .isTrue();
        assertThat(Primitives.ge(null, 0))
                .isFalse();
        assertThat(Primitives.ge(0, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Integer) null))
                .isFalse();
        assertThat(Primitives.ge((long) 1, (long) 0))
                .isTrue();
        assertThat(Primitives.ge(null, (long) 0))
                .isFalse();
        assertThat(Primitives.ge((long) 0, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Long) null))
                .isFalse();
        assertThat(Primitives.ge((float) 1.0, (float) 0.0))
                .isTrue();
        assertThat(Primitives.ge(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.ge((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Float) null))
                .isFalse();
        assertThat(Primitives.ge(1.0, 0.0))
                .isTrue();
        assertThat(Primitives.ge(null, 0.0))
                .isFalse();
        assertThat(Primitives.ge(0.0, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Double) null))
                .isFalse();
        assertThat(Primitives.ge(true, true))
                .isTrue();
        assertThat(Primitives.ge(false, false))
                .isTrue();
        assertThat(Primitives.ge(null, true))
                .isFalse();
        assertThat(Primitives.ge(true, null))
                .isFalse();
        assertThat(Primitives.ge(null, (Boolean) null))
                .isFalse();
    }

    @Test
    void testLt() {
        assertThat(Primitives.lt((byte) 0, (byte) 1))
                .isTrue();
        assertThat(Primitives.lt(null, (byte) 0))
                .isFalse();
        assertThat(Primitives.lt((byte) 0, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Byte) null))
                .isFalse();
        assertThat(Primitives.lt((short) 0, (short) 1))
                .isTrue();
        assertThat(Primitives.lt(null, (short) 0))
                .isFalse();
        assertThat(Primitives.lt((short) 0, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Short) null))
                .isFalse();
        assertThat(Primitives.lt(0, 1))
                .isTrue();
        assertThat(Primitives.lt(null, 0))
                .isFalse();
        assertThat(Primitives.lt(0, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Integer) null))
                .isFalse();
        assertThat(Primitives.lt((long) 0, (long) 1))
                .isTrue();
        assertThat(Primitives.lt(null, (long) 0))
                .isFalse();
        assertThat(Primitives.lt((long) 0, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Long) null))
                .isFalse();
        assertThat(Primitives.lt((float) 0.0, (float) 1.0))
                .isTrue();
        assertThat(Primitives.lt(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.lt((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Float) null))
                .isFalse();
        assertThat(Primitives.lt(0.0, 1.0))
                .isTrue();
        assertThat(Primitives.lt(null, 0.0))
                .isFalse();
        assertThat(Primitives.lt(0.0, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Double) null))
                .isFalse();
        assertThat(Primitives.lt(false, true))
                .isTrue();
        assertThat(Primitives.lt(true, true))
                .isFalse();
        assertThat(Primitives.lt(null, true))
                .isFalse();
        assertThat(Primitives.lt(true, null))
                .isFalse();
        assertThat(Primitives.lt(null, (Boolean) null))
                .isFalse();
    }

    @Test
    void testLe() {
        assertThat(Primitives.le((byte) 0, (byte) 1))
                .isTrue();
        assertThat(Primitives.le(null, (byte) 0))
                .isFalse();
        assertThat(Primitives.le((byte) 0, null))
                .isFalse();
        assertThat(Primitives.le(null, (Byte) null))
                .isFalse();
        assertThat(Primitives.le((short) 0, (short) 1))
                .isTrue();
        assertThat(Primitives.le(null, (short) 0))
                .isFalse();
        assertThat(Primitives.le((short) 0, null))
                .isFalse();
        assertThat(Primitives.le(null, (Short) null))
                .isFalse();
        assertThat(Primitives.le(0, 1))
                .isTrue();
        assertThat(Primitives.le(null, 0))
                .isFalse();
        assertThat(Primitives.le(0, null))
                .isFalse();
        assertThat(Primitives.le(null, (Integer) null))
                .isFalse();
        assertThat(Primitives.le((long) 0, (long) 1))
                .isTrue();
        assertThat(Primitives.le(null, (long) 0))
                .isFalse();
        assertThat(Primitives.le((long) 0, null))
                .isFalse();
        assertThat(Primitives.le(null, (Long) null))
                .isFalse();
        assertThat(Primitives.le((float) 0.0, (float) 1.0))
                .isTrue();
        assertThat(Primitives.le(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.le((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.le(null, (Float) null))
                .isFalse();
        assertThat(Primitives.le(0.0, 1.0))
                .isTrue();
        assertThat(Primitives.le(null, 0.0))
                .isFalse();
        assertThat(Primitives.le(0.0, null))
                .isFalse();
        assertThat(Primitives.le(null, (Double) null))
                .isFalse();
        assertThat(Primitives.le(true, true))
                .isTrue();
        assertThat(Primitives.le(false, false))
                .isTrue();
        assertThat(Primitives.le(null, true))
                .isFalse();
        assertThat(Primitives.le(true, null))
                .isFalse();
        assertThat(Primitives.le(null, (Boolean) null))
                .isFalse();
    }
}
