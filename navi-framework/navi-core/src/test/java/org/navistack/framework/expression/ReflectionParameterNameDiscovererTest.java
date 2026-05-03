package org.navistack.framework.expression;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionParameterNameDiscovererTest {
    private final ReflectionParameterNameDiscoverer discoverer = new ReflectionParameterNameDiscoverer();

    @Test
    void shouldReturnDeclaredParameterNamesInOrder() throws Exception {
        Method method = SampleMethods.class
                .getDeclaredMethod("withParams", String.class, int.class, Object.class);
        String[] names = discoverer.getParameterNames(method);
        assertThat(names).containsExactly("firstName", "age", "payload");
    }

    @Test
    void shouldReturnEmptyArrayForMethodWithNoParameters() throws Exception {
        Method method = SampleMethods.class.getDeclaredMethod("noParams");
        String[] names = discoverer.getParameterNames(method);
        assertThat(names).isEmpty();
    }

    @SuppressWarnings("unused")
    static class SampleMethods {
        static String withParams(String firstName, int age, Object payload) {
            return firstName;
        }

        static void noParams() {
        }
    }
}
