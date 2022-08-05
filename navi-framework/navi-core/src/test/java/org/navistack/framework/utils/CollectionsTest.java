package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionsTest {

    @Test
    void isEmpty() {
        assertThat(Collections.isEmpty(null)).isTrue();
        assertThat(Collections.isEmpty(java.util.Collections.emptyList())).isTrue();
        assertThat(Collections.isEmpty(java.util.Collections.singletonList(new Object()))).isFalse();
    }

    @Test
    void isNotEmpty() {
        assertThat(Collections.isNotEmpty(null)).isFalse();
        assertThat(Collections.isNotEmpty(java.util.Collections.emptyList())).isFalse();
        assertThat(Collections.isNotEmpty(java.util.Collections.singletonList(new Object()))).isTrue();
    }
}
