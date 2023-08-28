package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringsTest {
    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<Strings> constructor = Strings.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isEqualTo(true);
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance).getCause().isInstanceOf(UnsupportedOperationException.class);
        constructor.setAccessible(false);
    }

    @Test
    void strip() {
        assertThat(Strings.strip(null)).isEqualTo(null);
        assertThat(Strings.strip("")).isEqualTo("");
        assertThat(Strings.strip(" \t\r\n ")).isEqualTo("");
        assertThat(Strings.strip(" a b c ")).isEqualTo("a b c");
        assertThat(Strings.strip("\ta b c\t")).isEqualTo("a b c");
        assertThat(Strings.strip("\ta\tb\tc\t")).isEqualTo("a\tb\tc");
        assertThat(Strings.strip(" a b c ")).isEqualTo("a b c");
        assertThat(Strings.strip("\t a b c \t")).isEqualTo("a b c");
        assertThat(Strings.strip("\t a b c \r")).isEqualTo("a b c");
        assertThat(Strings.strip("\n a b c \n")).isEqualTo("a b c");
        assertThat(Strings.strip("\r\n a b c \r\n")).isEqualTo("a b c");
    }

    @Test
    void stripLeading() {
        assertThat(Strings.stripLeading(null)).isEqualTo(null);
        assertThat(Strings.stripLeading("")).isEqualTo("");
        assertThat(Strings.stripLeading(" \t\r\n")).isEqualTo("");
        assertThat(Strings.stripLeading(" a b c ")).isEqualTo("a b c ");
        assertThat(Strings.stripLeading("\ra b c ")).isEqualTo("a b c ");
        assertThat(Strings.stripLeading("\na b c ")).isEqualTo("a b c ");
        assertThat(Strings.stripLeading("\r\na b c ")).isEqualTo("a b c ");
    }

    @Test
    void stripTrailing() {
        assertThat(Strings.stripTrailing(null)).isEqualTo(null);
        assertThat(Strings.stripTrailing("")).isEqualTo("");
        assertThat(Strings.stripTrailing(" \t\r\n")).isEqualTo("");
        assertThat(Strings.stripTrailing(" a b c ")).isEqualTo(" a b c");
        assertThat(Strings.stripTrailing(" a b c\t")).isEqualTo(" a b c");
        assertThat(Strings.stripTrailing(" a b c\r")).isEqualTo(" a b c");
        assertThat(Strings.stripTrailing(" a b c\n")).isEqualTo(" a b c");
        assertThat(Strings.stripTrailing(" a b c\r\n")).isEqualTo(" a b c");
    }

    @Test
    void hasLength() {
        assertThat(Strings.hasLength(null)).isFalse();
        assertThat(Strings.hasLength("")).isFalse();
        assertThat(Strings.hasLength(" ")).isTrue();
        assertThat(Strings.hasLength("\t")).isTrue();
        assertThat(Strings.hasLength("\r")).isTrue();
        assertThat(Strings.hasLength("\n")).isTrue();
        assertThat(Strings.hasLength("\r\n")).isTrue();
        assertThat(Strings.hasLength("abc")).isTrue();
    }

    @Test
    void hasText() {
        assertThat(Strings.hasText(null)).isFalse();
        assertThat(Strings.hasText("")).isFalse();
        assertThat(Strings.hasText("\t  \r\n   ")).isFalse();
        assertThat(Strings.hasText("null")).isTrue();
        assertThat(Strings.hasText("abc")).isTrue();
        assertThat(Strings.hasText(" abc")).isTrue();
        assertThat(Strings.hasText("abc ")).isTrue();
        assertThat(Strings.hasText(" abc ")).isTrue();
        assertThat(Strings.hasText(" a b c ")).isTrue();
    }

    @Test
    void split() {
        assertThat(Strings.split(null, ":")).isNotNull().isEmpty();
        assertThat(Strings.split("", ":")).isNotNull().isEmpty();
        assertThat(Strings.split(":", ":")).isNotNull().isEmpty();
        assertThat(Strings.split("a:b", ":")).containsExactly("a", "b");
        assertThat(Strings.split("a:", ":")).containsExactly("a");
        assertThat(Strings.split(":b", ":")).containsExactly("", "b");
    }

    @Test
    void splitLimit() {
        assertThat(Strings.split(null, ":", 2)).isNotNull().isEmpty();
        assertThat(Strings.split("", ":", 2)).isNotNull().isEmpty();
        assertThat(Strings.split(":", ":", 2)).containsExactly("", "");
        assertThat(Strings.split("a:b:c", ":", 2)).containsExactly("a", "b:c");
        assertThat(Strings.split("a:b:", ":", 2)).containsExactly("a", "b:");
        assertThat(Strings.split(":b:c", ":", 2)).containsExactly("", "b:c");
    }

    @Test
    void join() {
        assertThat(Strings.join(".", "a", "b")).isEqualTo("a.b");
        assertThat(Strings.join(".", "a", null)).isEqualTo("a.null");
        assertThat(Strings.join(".")).isEqualTo("");
        assertThatThrownBy(() -> Strings.join(null, "a", "b", "c")).isInstanceOf(NullPointerException.class);
    }
}
