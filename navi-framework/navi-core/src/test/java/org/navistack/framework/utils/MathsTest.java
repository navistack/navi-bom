package org.navistack.framework.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MathsTest {
    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<Maths> constructor = Maths.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isEqualTo(true);
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance).getCause().isInstanceOf(UnsupportedOperationException.class);
        constructor.setAccessible(false);
    }

    @Test
    void clampLong() {
        Assertions.assertThat(Maths.clamp(1L, 5L, 15L)).isEqualTo(5L);
        Assertions.assertThat(Maths.clamp(10L, 5L, 15L)).isEqualTo(10L);
        Assertions.assertThat(Maths.clamp(20L, 5L, 15L)).isEqualTo(15L);
    }

    @Test
    void clampInt() {
        Assertions.assertThat(Maths.clamp(1, 5, 15)).isEqualTo(5);
        Assertions.assertThat(Maths.clamp(10, 5, 15)).isEqualTo(10);
        Assertions.assertThat(Maths.clamp(20, 5, 15)).isEqualTo(15);
    }

    @Test
    void clampDouble() {
        Assertions.assertThat(Maths.clamp(1.0, 5.0, 15.0)).isEqualTo(5.0f);
        Assertions.assertThat(Maths.clamp(10.0, 5.0, 15.0)).isEqualTo(10.0f);
        Assertions.assertThat(Maths.clamp(20.0, 5.0, 15.0)).isEqualTo(15.0f);
    }

    @Test
    void clampFloat() {
        Assertions.assertThat(Maths.clamp(1.0f, 5.0f, 15.0f)).isEqualTo(5.0f);
        Assertions.assertThat(Maths.clamp(10.0f, 5.0f, 15.0f)).isEqualTo(10.0f);
        Assertions.assertThat(Maths.clamp(20.0f, 5.0f, 15.0f)).isEqualTo(15.0f);
    }
}
