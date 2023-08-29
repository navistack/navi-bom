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
        assertThat(Primitives.eq((Byte) null, (Byte) null))
                .isFalse();
        assertThat(Primitives.eq((short) 0, (short) 0))
                .isTrue();
        assertThat(Primitives.eq(null, (short) 0))
                .isFalse();
        assertThat(Primitives.eq((short) 0, null))
                .isFalse();
        assertThat(Primitives.eq((Short) null, (Short) null))
                .isFalse();
        assertThat(Primitives.eq((int) 0, (int) 0))
                .isTrue();
        assertThat(Primitives.eq(null, (int) 0))
                .isFalse();
        assertThat(Primitives.eq((int) 0, null))
                .isFalse();
        assertThat(Primitives.eq((Integer) null, (Integer) null))
                .isFalse();
        assertThat(Primitives.eq((long) 0, (long) 0))
                .isTrue();
        assertThat(Primitives.eq(null, (long) 0))
                .isFalse();
        assertThat(Primitives.eq((long) 0, null))
                .isFalse();
        assertThat(Primitives.eq((Long) null, (Long) null))
                .isFalse();
        assertThat(Primitives.eq((float) 0.0, (float) 0.0))
                .isTrue();
        assertThat(Primitives.eq(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.eq((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.eq((Float) null, (Float) null))
                .isFalse();
        assertThat(Primitives.eq((double) 0.0, (double) 0.0))
                .isTrue();
        assertThat(Primitives.eq(null, (double) 0.0))
                .isFalse();
        assertThat(Primitives.eq((double) 0.0, null))
                .isFalse();
        assertThat(Primitives.eq((Double) null, (Double) null))
                .isFalse();
        assertThat(Primitives.eq((boolean) true, (boolean) true))
                .isTrue();
        assertThat(Primitives.eq((boolean) false, (boolean) false))
                .isTrue();
        assertThat(Primitives.eq(null, (boolean) true))
                .isFalse();
        assertThat(Primitives.eq((boolean) true, null))
                .isFalse();
        assertThat(Primitives.eq((Boolean) null, (Boolean) null))
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
        assertThat(Primitives.neq((Byte) null, (Byte) null))
                .isTrue();
        assertThat(Primitives.neq((short) 0, (short) 0))
                .isFalse();
        assertThat(Primitives.neq(null, (short) 0))
                .isTrue();
        assertThat(Primitives.neq((short) 0, null))
                .isTrue();
        assertThat(Primitives.neq((Short) null, (Short) null))
                .isTrue();
        assertThat(Primitives.neq((int) 0, (int) 0))
                .isFalse();
        assertThat(Primitives.neq(null, (int) 0))
                .isTrue();
        assertThat(Primitives.neq((int) 0, null))
                .isTrue();
        assertThat(Primitives.neq((Integer) null, (Integer) null))
                .isTrue();
        assertThat(Primitives.neq((long) 0, (long) 0))
                .isFalse();
        assertThat(Primitives.neq(null, (long) 0))
                .isTrue();
        assertThat(Primitives.neq((long) 0, null))
                .isTrue();
        assertThat(Primitives.neq((Long) null, (Long) null))
                .isTrue();
        assertThat(Primitives.neq((float) 0.0, (float) 0.0))
                .isFalse();
        assertThat(Primitives.neq(null, (float) 0.0))
                .isTrue();
        assertThat(Primitives.neq((float) 0.0, null))
                .isTrue();
        assertThat(Primitives.neq((Float) null, (Float) null))
                .isTrue();
        assertThat(Primitives.neq((double) 0.0, (double) 0.0))
                .isFalse();
        assertThat(Primitives.neq(null, (double) 0.0))
                .isTrue();
        assertThat(Primitives.neq((double) 0.0, null))
                .isTrue();
        assertThat(Primitives.neq((Double) null, (Double) null))
                .isTrue();
        assertThat(Primitives.neq((boolean) true, (boolean) true))
                .isFalse();
        assertThat(Primitives.neq((boolean) false, (boolean) false))
                .isFalse();
        assertThat(Primitives.neq(null, (boolean) true))
                .isTrue();
        assertThat(Primitives.neq((boolean) true, null))
                .isTrue();
        assertThat(Primitives.neq((Boolean) null, (Boolean) null))
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
        assertThat(Primitives.gt((Byte) null, (Byte) null))
                .isFalse();
        assertThat(Primitives.gt((short) 1, (short) 0))
                .isTrue();
        assertThat(Primitives.gt(null, (short) 0))
                .isFalse();
        assertThat(Primitives.gt((short) 0, null))
                .isFalse();
        assertThat(Primitives.gt((Short) null, (Short) null))
                .isFalse();
        assertThat(Primitives.gt((int) 1, (int) 0))
                .isTrue();
        assertThat(Primitives.gt(null, (int) 0))
                .isFalse();
        assertThat(Primitives.gt((int) 0, null))
                .isFalse();
        assertThat(Primitives.gt((Integer) null, (Integer) null))
                .isFalse();
        assertThat(Primitives.gt((long) 1, (long) 0))
                .isTrue();
        assertThat(Primitives.gt(null, (long) 0))
                .isFalse();
        assertThat(Primitives.gt((long) 0, null))
                .isFalse();
        assertThat(Primitives.gt((Long) null, (Long) null))
                .isFalse();
        assertThat(Primitives.gt((float) 1.0, (float) 0.0))
                .isTrue();
        assertThat(Primitives.gt(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.gt((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.gt((Float) null, (Float) null))
                .isFalse();
        assertThat(Primitives.gt((double) 1.0, (double) 0.0))
                .isTrue();
        assertThat(Primitives.gt(null, (double) 0.0))
                .isFalse();
        assertThat(Primitives.gt((double) 0.0, null))
                .isFalse();
        assertThat(Primitives.gt((Double) null, (Double) null))
                .isFalse();
        assertThat(Primitives.gt((boolean) true, (boolean) false))
                .isTrue();
        assertThat(Primitives.gt((boolean) false, (boolean) false))
                .isFalse();
        assertThat(Primitives.gt(null, (boolean) true))
                .isFalse();
        assertThat(Primitives.gt((boolean) true, null))
                .isFalse();
        assertThat(Primitives.gt((Boolean) null, (Boolean) null))
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
        assertThat(Primitives.ge((Byte) null, (Byte) null))
                .isFalse();
        assertThat(Primitives.ge((short) 1, (short) 0))
                .isTrue();
        assertThat(Primitives.ge(null, (short) 0))
                .isFalse();
        assertThat(Primitives.ge((short) 0, null))
                .isFalse();
        assertThat(Primitives.ge((Short) null, (Short) null))
                .isFalse();
        assertThat(Primitives.ge((int) 1, (int) 0))
                .isTrue();
        assertThat(Primitives.ge(null, (int) 0))
                .isFalse();
        assertThat(Primitives.ge((int) 0, null))
                .isFalse();
        assertThat(Primitives.ge((Integer) null, (Integer) null))
                .isFalse();
        assertThat(Primitives.ge((long) 1, (long) 0))
                .isTrue();
        assertThat(Primitives.ge(null, (long) 0))
                .isFalse();
        assertThat(Primitives.ge((long) 0, null))
                .isFalse();
        assertThat(Primitives.ge((Long) null, (Long) null))
                .isFalse();
        assertThat(Primitives.ge((float) 1.0, (float) 0.0))
                .isTrue();
        assertThat(Primitives.ge(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.ge((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.ge((Float) null, (Float) null))
                .isFalse();
        assertThat(Primitives.ge((double) 1.0, (double) 0.0))
                .isTrue();
        assertThat(Primitives.ge(null, (double) 0.0))
                .isFalse();
        assertThat(Primitives.ge((double) 0.0, null))
                .isFalse();
        assertThat(Primitives.ge((Double) null, (Double) null))
                .isFalse();
        assertThat(Primitives.ge((boolean) true, (boolean) true))
                .isTrue();
        assertThat(Primitives.ge((boolean) false, (boolean) false))
                .isTrue();
        assertThat(Primitives.ge(null, (boolean) true))
                .isFalse();
        assertThat(Primitives.ge((boolean) true, null))
                .isFalse();
        assertThat(Primitives.ge((Boolean) null, (Boolean) null))
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
        assertThat(Primitives.lt((Byte) null, (Byte) null))
                .isFalse();
        assertThat(Primitives.lt((short) 0, (short) 1))
                .isTrue();
        assertThat(Primitives.lt(null, (short) 0))
                .isFalse();
        assertThat(Primitives.lt((short) 0, null))
                .isFalse();
        assertThat(Primitives.lt((Short) null, (Short) null))
                .isFalse();
        assertThat(Primitives.lt((int) 0, (int) 1))
                .isTrue();
        assertThat(Primitives.lt(null, (int) 0))
                .isFalse();
        assertThat(Primitives.lt((int) 0, null))
                .isFalse();
        assertThat(Primitives.lt((Integer) null, (Integer) null))
                .isFalse();
        assertThat(Primitives.lt((long) 0, (long) 1))
                .isTrue();
        assertThat(Primitives.lt(null, (long) 0))
                .isFalse();
        assertThat(Primitives.lt((long) 0, null))
                .isFalse();
        assertThat(Primitives.lt((Long) null, (Long) null))
                .isFalse();
        assertThat(Primitives.lt((float) 0.0, (float) 1.0))
                .isTrue();
        assertThat(Primitives.lt(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.lt((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.lt((Float) null, (Float) null))
                .isFalse();
        assertThat(Primitives.lt((double) 0.0, (double) 1.0))
                .isTrue();
        assertThat(Primitives.lt(null, (double) 0.0))
                .isFalse();
        assertThat(Primitives.lt((double) 0.0, null))
                .isFalse();
        assertThat(Primitives.lt((Double) null, (Double) null))
                .isFalse();
        assertThat(Primitives.lt((boolean) false, (boolean) true))
                .isTrue();
        assertThat(Primitives.lt((boolean) true, (boolean) true))
                .isFalse();
        assertThat(Primitives.lt(null, (boolean) true))
                .isFalse();
        assertThat(Primitives.lt((boolean) true, null))
                .isFalse();
        assertThat(Primitives.lt((Boolean) null, (Boolean) null))
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
        assertThat(Primitives.le((Byte) null, (Byte) null))
                .isFalse();
        assertThat(Primitives.le((short) 0, (short) 1))
                .isTrue();
        assertThat(Primitives.le(null, (short) 0))
                .isFalse();
        assertThat(Primitives.le((short) 0, null))
                .isFalse();
        assertThat(Primitives.le((Short) null, (Short) null))
                .isFalse();
        assertThat(Primitives.le((int) 0, (int) 1))
                .isTrue();
        assertThat(Primitives.le(null, (int) 0))
                .isFalse();
        assertThat(Primitives.le((int) 0, null))
                .isFalse();
        assertThat(Primitives.le((Integer) null, (Integer) null))
                .isFalse();
        assertThat(Primitives.le((long) 0, (long) 1))
                .isTrue();
        assertThat(Primitives.le(null, (long) 0))
                .isFalse();
        assertThat(Primitives.le((long) 0, null))
                .isFalse();
        assertThat(Primitives.le((Long) null, (Long) null))
                .isFalse();
        assertThat(Primitives.le((float) 0.0, (float) 1.0))
                .isTrue();
        assertThat(Primitives.le(null, (float) 0.0))
                .isFalse();
        assertThat(Primitives.le((float) 0.0, null))
                .isFalse();
        assertThat(Primitives.le((Float) null, (Float) null))
                .isFalse();
        assertThat(Primitives.le((double) 0.0, (double) 1.0))
                .isTrue();
        assertThat(Primitives.le(null, (double) 0.0))
                .isFalse();
        assertThat(Primitives.le((double) 0.0, null))
                .isFalse();
        assertThat(Primitives.le((Double) null, (Double) null))
                .isFalse();
        assertThat(Primitives.le((boolean) true, (boolean) true))
                .isTrue();
        assertThat(Primitives.le((boolean) false, (boolean) false))
                .isTrue();
        assertThat(Primitives.le(null, (boolean) true))
                .isFalse();
        assertThat(Primitives.le((boolean) true, null))
                .isFalse();
        assertThat(Primitives.le((Boolean) null, (Boolean) null))
                .isFalse();
    }
}
