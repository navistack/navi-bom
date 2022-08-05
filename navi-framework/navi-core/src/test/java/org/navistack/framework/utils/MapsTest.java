package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class MapsTest {

    @Test
    void isEmpty() {
        assertThat(Maps.isEmpty(null)).isTrue();
        assertThat(Maps.isEmpty(Collections.emptyMap())).isTrue();
        assertThat(Maps.isEmpty(Collections.singletonMap("key", "value"))).isFalse();
    }

    @Test
    void isNotEmpty() {
        assertThat(Maps.isNotEmpty(null)).isFalse();
        assertThat(Maps.isNotEmpty(Collections.emptyMap())).isFalse();
        assertThat(Maps.isNotEmpty(Collections.singletonMap("key", "value"))).isTrue();
    }
}
