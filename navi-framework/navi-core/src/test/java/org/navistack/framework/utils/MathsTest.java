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
    void clampShort() {
        Assertions.assertThat(Maths.clamp((short) 1, (short) 5, (short) 15)).isEqualTo((short) 5);
        Assertions.assertThat(Maths.clamp((short) 10, (short) 5, (short) 15)).isEqualTo((short) 10);
        Assertions.assertThat(Maths.clamp((short) 20, (short) 5, (short) 15)).isEqualTo((short) 15);
    }
}
