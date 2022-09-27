package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void shift() {
        String[] result = Arrays.shift(new String[]{"a", "b", "c"}, 1, String[]::new);
        assertThat(Arrays.shift(new String[]{"a", "b", "c"}, 1, String[]::new)).containsExactly(null, "a", "b", "c");
        assertThat(Arrays.shift(new String[]{}, 1, String[]::new)).isEmpty();
        assertThatThrownBy(() -> Arrays.shift(new String[]{"a", "b", "c"}, 0, String[]::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Arrays.shift(new String[]{"a", "b", "c"}, -1, String[]::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Arrays.shift(null, 1, String[]::new)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void asArray() {
        Integer[] result = Arrays.asArray(1);
        assertThat(Arrays.asArray(1)).isNotNull().containsExactly(1);
        assertThat(Arrays.asArray(1, 2)).isNotNull().containsExactly(1, 2);
        assertThat(Arrays.asArray(1, 2, 3)).isNotNull().containsExactly(1, 2, 3);
    }
}
