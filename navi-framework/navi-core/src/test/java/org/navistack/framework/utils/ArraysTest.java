package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArraysTest {
    @Test
    void get() {
        assertThat(Arrays.get(new String[]{ "str1", "str2" }, -1)).isNull();
        assertThat(Arrays.get(new String[]{ "str1", "str2" }, 0)).isEqualTo("str1");
        assertThat(Arrays.get(new String[]{ "str1", "str2" }, 1)).isEqualTo("str2");
        assertThat(Arrays.get(new String[]{ "str1", "str2" }, 2)).isNull();
        assertThat(Arrays.get((String[])null, -1)).isNull();
        assertThat(Arrays.get((String[])null, 0)).isNull();
        assertThat(Arrays.get((String[])null, 1)).isNull();
        assertThat(Arrays.get((String[])null, 2)).isNull();
    }

    @Test
    void isEmpty() {
        assertThat(Arrays.isEmpty((String[])null)).isTrue();
        assertThat(Arrays.isEmpty(new String[]{})).isTrue();
        assertThat(Arrays.isEmpty(new String[]{ "str1", "str2"})).isFalse();
    }

    @Test
    void isNotEmpty() {
        assertThat(Arrays.isNotEmpty((String[])null)).isFalse();
        assertThat(Arrays.isNotEmpty(new String[]{})).isFalse();
        assertThat(Arrays.isNotEmpty(new String[]{ "str1", "str2"})).isTrue();
    }
}
