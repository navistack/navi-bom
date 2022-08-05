package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectsTest {

    @Test
    void isNull() {
        assertThat(Objects.isNull(null)).isTrue();
        assertThat(Objects.isNull(new Object())).isFalse();
    }

    @Test
    void isNotNull() {
        assertThat(Objects.isNotNull(null)).isFalse();
        assertThat(Objects.isNotNull(new Object())).isTrue();
    }

    @Test
    void testEquals() {
        String str = "str";
        Object cStr = "s" + "t" + "r";
        Object rStr = "rts";
        assertThat(Objects.equals(null, null)).isFalse();
        assertThat(Objects.equals(str, null)).isFalse();
        assertThat(Objects.equals(null, cStr)).isFalse();
        assertThat(Objects.equals(str, rStr)).isFalse();
        assertThat(Objects.equals(cStr, rStr)).isFalse();
        assertThat(Objects.equals(str, cStr)).isTrue();
        assertThat(Objects.equals(str, str)).isTrue();
        assertThat(Objects.equals(cStr, cStr)).isTrue();
        assertThat(Objects.equals(str, cStr)).isTrue();
    }
}
