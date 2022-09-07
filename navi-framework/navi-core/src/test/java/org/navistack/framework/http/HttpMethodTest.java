package org.navistack.framework.http;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HttpMethodTest {

    @Test
    void testValueOf() {
        Assertions.assertThat(HttpMethod.valueOf("GET")).isSameAs(HttpMethod.GET);
        Assertions.assertThat(HttpMethod.valueOf("HEAD")).isSameAs(HttpMethod.HEAD);
        Assertions.assertThat(HttpMethod.valueOf("POST")).isSameAs(HttpMethod.POST);
        Assertions.assertThat(HttpMethod.valueOf("PUT")).isSameAs(HttpMethod.PUT);
        Assertions.assertThat(HttpMethod.valueOf("PATCH")).isSameAs(HttpMethod.PATCH);
        Assertions.assertThat(HttpMethod.valueOf("DELETE")).isSameAs(HttpMethod.DELETE);
        Assertions.assertThat(HttpMethod.valueOf("OPTIONS")).isSameAs(HttpMethod.OPTIONS);
        Assertions.assertThat(HttpMethod.valueOf("LOCK")).extracting(HttpMethod::getName).isEqualTo("LOCK");
        Assertions.assertThatThrownBy(() -> HttpMethod.valueOf(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testEquals() {
        Assertions.assertThat(HttpMethod.valueOf("GET")).isEqualTo(HttpMethod.valueOf("GET"));
        Assertions.assertThat(HttpMethod.valueOf("HEAD")).isEqualTo(HttpMethod.valueOf("HEAD"));
        Assertions.assertThat(HttpMethod.valueOf("POST")).isEqualTo(HttpMethod.valueOf("POST"));
        Assertions.assertThat(HttpMethod.valueOf("PUT")).isEqualTo(HttpMethod.valueOf("PUT"));
        Assertions.assertThat(HttpMethod.valueOf("PATCH")).isEqualTo(HttpMethod.valueOf("PATCH"));
        Assertions.assertThat(HttpMethod.valueOf("DELETE")).isEqualTo(HttpMethod.valueOf("DELETE"));
        Assertions.assertThat(HttpMethod.valueOf("OPTIONS")).isEqualTo(HttpMethod.valueOf("OPTIONS"));
        Assertions.assertThat(HttpMethod.valueOf("LOCK")).isNotSameAs(HttpMethod.valueOf("LOCK")).isEqualTo(HttpMethod.valueOf("LOCK"));
    }
}
