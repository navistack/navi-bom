package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MediaTypesTest {

    @Test
    void of() {
        MediaType any = MediaTypes.of("*", "*");
        assertThat(any).isNotNull();
        assertThat(any.getFullType()).isEqualTo("*/*");
        assertThat(any.getType()).isEqualTo("*");
        assertThat(any.getSubType()).isEqualTo("*");
    }

    @Test
    void valueOf() {
        assertThat(MediaTypes.valueOf("application", "json")).isSameAs(MediaTypes.APPLICATION_JSON);
        assertThat(MediaTypes.valueOf("application/json")).isSameAs(MediaTypes.APPLICATION_JSON);
        assertThat(MediaTypes.valueOf("example", "*")).isNull();
        assertThat(MediaTypes.valueOf("example/*")).isNull();
    }

    @Test
    void optionalValueOf() {
        assertThat(MediaTypes.optionalValueOf("application", "json")).isNotNull().isPresent().containsSame(MediaTypes.APPLICATION_JSON);
        assertThat(MediaTypes.optionalValueOf("application/json")).isNotNull().isPresent().containsSame(MediaTypes.APPLICATION_JSON);
        assertThat(MediaTypes.optionalValueOf("example", "*")).isNotNull().isNotPresent();
        assertThat(MediaTypes.optionalValueOf("example/*")).isNotNull().isNotPresent();
    }
}
