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
    void trimWhitespace() {
        assertThat(Strings.trimWhitespace(null)).isEqualTo(null);
        assertThat(Strings.trimWhitespace("")).isEqualTo("");
        assertThat(Strings.trimWhitespace(" \t\r\n ")).isEqualTo("");
        assertThat(Strings.trimWhitespace(" a b c ")).isEqualTo("a b c");
        assertThat(Strings.trimWhitespace("\ta b c\t")).isEqualTo("a b c");
        assertThat(Strings.trimWhitespace("\ta\tb\tc\t")).isEqualTo("a\tb\tc");
        assertThat(Strings.trimWhitespace(" a b c ")).isEqualTo("a b c");
        assertThat(Strings.trimWhitespace("\t a b c \t")).isEqualTo("a b c");
        assertThat(Strings.trimWhitespace("\t a b c \r")).isEqualTo("a b c");
        assertThat(Strings.trimWhitespace("\n a b c \n")).isEqualTo("a b c");
        assertThat(Strings.trimWhitespace("\r\n a b c \r\n")).isEqualTo("a b c");
    }

    @Test
    void trimLeadingWhitespace() {
        assertThat(Strings.trimLeadingWhitespace(null)).isEqualTo(null);
        assertThat(Strings.trimLeadingWhitespace("")).isEqualTo("");
        assertThat(Strings.trimLeadingWhitespace(" \t\r\n")).isEqualTo("");
        assertThat(Strings.trimLeadingWhitespace(" a b c ")).isEqualTo("a b c ");
        assertThat(Strings.trimLeadingWhitespace("\ra b c ")).isEqualTo("a b c ");
        assertThat(Strings.trimLeadingWhitespace("\na b c ")).isEqualTo("a b c ");
        assertThat(Strings.trimLeadingWhitespace("\r\na b c ")).isEqualTo("a b c ");
    }

    @Test
    void trimTrailingWhitespace() {
        assertThat(Strings.trimTrailingWhitespace(null)).isEqualTo(null);
        assertThat(Strings.trimTrailingWhitespace("")).isEqualTo("");
        assertThat(Strings.trimTrailingWhitespace(" \t\r\n")).isEqualTo("");
        assertThat(Strings.trimTrailingWhitespace(" a b c ")).isEqualTo(" a b c");
        assertThat(Strings.trimTrailingWhitespace(" a b c\t")).isEqualTo(" a b c");
        assertThat(Strings.trimTrailingWhitespace(" a b c\r")).isEqualTo(" a b c");
        assertThat(Strings.trimTrailingWhitespace(" a b c\n")).isEqualTo(" a b c");
        assertThat(Strings.trimTrailingWhitespace(" a b c\r\n")).isEqualTo(" a b c");
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
}
