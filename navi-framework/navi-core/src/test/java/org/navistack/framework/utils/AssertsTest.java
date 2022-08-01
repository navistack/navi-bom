package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssertsTest {
    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<Asserts> constructor = Asserts.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isEqualTo(true);
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance).getCause().isInstanceOf(UnsupportedOperationException.class);
        constructor.setAccessible(false);
    }

    @Test
    void state() {
        Asserts.state(true, RuntimeException::new);
        assertThatThrownBy(() -> Asserts.state(false, RuntimeException::new)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void isNull() {
        Asserts.isNull(null, IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.isNull(new Object(), IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.isNull("", IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.isNull(1, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.isNull(true, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.isNull(false, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void notNull() {
        Asserts.notNull(0, IllegalArgumentException::new);
        Asserts.notNull(true, IllegalArgumentException::new);
        Asserts.notNull(false, IllegalArgumentException::new);
        Asserts.notNull("", IllegalArgumentException::new);
        Asserts.notNull(new Object(), IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.notNull(null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void doesEqual() {
        Object obj = new Object();
        Asserts.doesEqual(obj, obj, IllegalArgumentException::new);
        String str = "str";
        Asserts.doesEqual(str, str, IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.doesEqual(null, null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.doesEqual(new Object(), null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.doesEqual(null, new Object(), IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.doesEqual(new Object(), new Object(), IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void notEmptyString() {
        Asserts.notEmpty("str", IllegalArgumentException::new);
        Asserts.notEmpty(" ", IllegalArgumentException::new);
        Asserts.notEmpty("\t", IllegalArgumentException::new);
        Asserts.notEmpty("\r", IllegalArgumentException::new);
        Asserts.notEmpty("\n", IllegalArgumentException::new);
        Asserts.notEmpty("\r\n", IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.notEmpty((String) null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.notEmpty("", IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void notEmptyCollection() {
        Collection<String> coll = Arrays.asList("a", "b", "c");
        Asserts.notEmpty(coll, IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.notEmpty((Collection<?>) null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.notEmpty(Collections.emptyList(), IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void notEmptyMap() {
        Map<String, String> map = Collections.singletonMap("key", "value");
        Asserts.notEmpty(map, IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.notEmpty((Map<?, ?>) null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.notEmpty(Collections.emptyMap(), IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void notEmptyArray() {
        Object[] arr = new Object[]{new Object()};
        Asserts.notEmpty(arr, IllegalArgumentException::new);
        assertThatThrownBy(() -> Asserts.notEmpty((Object[]) null, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Asserts.notEmpty(new Object[]{}, IllegalArgumentException::new)).isInstanceOf(IllegalArgumentException.class);
    }
}
