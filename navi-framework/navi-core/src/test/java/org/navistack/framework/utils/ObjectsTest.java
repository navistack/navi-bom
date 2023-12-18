package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void firstNonNull() {
        assertThat(Objects.firstNonNull((Object) null)).isNull();
        assertThat(Objects.firstNonNull((Object[]) null)).isNull();
        assertThat(Objects.firstNonNull(null, (Object) null)).isNull();
        assertThat(Objects.firstNonNull(null, "str")).isEqualTo("str");
        assertThat(Objects.firstNonNull(null, null, "str")).isEqualTo("str");
        assertThat(Objects.firstNonNull(null, "str1", null, null, "str2")).isEqualTo("str1");
    }

    @Test
    void requireNull() {
        assertThatCode(() -> Objects.requireNull(null)).doesNotThrowAnyException();
        assertThatThrownBy(() -> Objects.requireNull(new Object())).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Objects.requireNull(new Object(), "Object must be null")).isInstanceOf(IllegalArgumentException.class).hasMessage("Object must be null");
    }

    @Test
    void requireNonNull() {
        assertThatCode(() -> Objects.requireNonNull(new Object())).doesNotThrowAnyException();
        assertThatThrownBy(() -> Objects.requireNonNull(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Objects.requireNonNull(null, "Object must not be null")).isInstanceOf(NullPointerException.class);
    }
}
