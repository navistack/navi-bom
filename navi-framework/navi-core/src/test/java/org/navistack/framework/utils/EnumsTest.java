package org.navistack.framework.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.navistack.framework.core.Numeric;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnumsTest {

    enum HttpMethod {
        GET,
        HEAD,
        POST,
        PUT,
        DELETE,
        CONNECT,
        OPTIONS,
        TRACE,
        PATCH,
    }

    @Getter
    @Accessors(fluent = true)
    @AllArgsConstructor
    enum HttpStatus implements Numeric<Integer> {
        CONTINUE(100),
        SWITCHING_PROTOCOLS(101),
        PROCESSING(102),
        EARLY_HINTS_EXPERIMENTAL(103);

        private final Integer numeral;
    }

    @Test
    void valueOf_shouldThrowNullPointerExceptionWhenClassIsNull() {
        assertThatThrownBy(() -> Enums.valueOf(null, "GET"))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Enums.valueOf(null, 0))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void valueOf_shouldThrowIllegalArgumentExceptionWhenCorrespondingEnumNotExists() {
        assertThatThrownBy(() -> Enums.valueOf(HttpMethod.class, null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enums.valueOf(HttpMethod.class, ""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enums.valueOf(HttpMethod.class, "OPTION"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enums.valueOf(HttpStatus.class, (Integer) null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enums.valueOf(HttpStatus.class, 199))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void valueOf_shouldReturnExpectedEnum() {
        assertThat(Enums.valueOf(HttpMethod.class, "GET"))
                .isSameAs(HttpMethod.GET);
        assertThat(Enums.valueOf(HttpStatus.class, 100))
                .isSameAs(HttpStatus.CONTINUE);
    }

    @Test
    void valueOf_shouldReturnDefaultEnumWhenCorrespondingEnumNotExists() {
        assertThat(Enums.valueOf(HttpMethod.class, null, HttpMethod.GET))
                .isSameAs(HttpMethod.GET);
        assertThat(Enums.valueOf(HttpMethod.class, "", HttpMethod.GET))
                .isSameAs(HttpMethod.GET);
        assertThat(Enums.valueOf(HttpMethod.class, "OPTION", HttpMethod.GET))
                .isSameAs(HttpMethod.GET);
        assertThat(Enums.valueOf(HttpStatus.class, (Integer) null, HttpStatus.CONTINUE))
                .isSameAs(HttpStatus.CONTINUE);
        assertThat(Enums.valueOf(HttpStatus.class, 199, HttpStatus.CONTINUE))
                .isSameAs(HttpStatus.CONTINUE);
    }

    @Test
    void optionalValueOf_shouldReturnExpectedEnum() {
        assertThat(Enums.optionalValueOf(HttpMethod.class, "GET"))
                .isNotNull()
                .hasValue(HttpMethod.GET);
        assertThat(Enums.optionalValueOf(HttpStatus.class, 100))
                .isNotNull()
                .hasValue(HttpStatus.CONTINUE);
    }

    @Test
    void optionalValueOf_shouldReturnEmptyOptionalWhenCorrespondingEnumNotExists() {
        assertThat(Enums.optionalValueOf(HttpMethod.class, "OPTION"))
                .isNotNull()
                .isEmpty();
        assertThat(Enums.optionalValueOf(HttpStatus.class, 199))
                .isNotNull()
                .isEmpty();
    }

    @Test
    void nullableValueOf_shouldReturnExpectedEnum() {
        assertThat(Enums.nullableValueOf(HttpMethod.class, "GET"))
                .isNotNull();
        assertThat(Enums.nullableValueOf(HttpStatus.class, 100))
                .isNotNull();
    }

    @Test
    void nullableValueOf_shouldReturnNullWhenCorrespondingEnumNotExists() {
        assertThat(Enums.nullableValueOf(HttpMethod.class, "OPTION"))
                .isNull();
        assertThat(Enums.nullableValueOf(HttpStatus.class, 199))
                .isNull();
    }
}
