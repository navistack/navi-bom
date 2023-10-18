package org.navistack.framework.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooleansTest {

    @Test
    void isTrue_shouldReturnTrueWhenValueIsTrue() {
        Assertions.assertThat(Booleans.isTrue(true))
                .isTrue();
        Assertions.assertThat(Booleans.isTrue(Boolean.TRUE))
                .isTrue();
    }

    @Test
    void isTrue_shouldReturnFalseWhenValueIsFalse() {
        Assertions.assertThat(Booleans.isTrue(false))
                .isFalse();
        Assertions.assertThat(Booleans.isTrue(Boolean.FALSE))
                .isFalse();
    }

    @Test
    void isTrue_shouldReturnFalseWhenValueIsNull() {
        Assertions.assertThat(Booleans.isTrue(null))
                .isFalse();
    }

    @Test
    void isFalse_shouldReturnFalseWhenValueIsTrue() {
        Assertions.assertThat(Booleans.isFalse(true))
                .isFalse();
        Assertions.assertThat(Booleans.isFalse(Boolean.TRUE))
                .isFalse();
    }

    @Test
    void isFalse_shouldReturnTrueWhenValueIsFalse() {
        Assertions.assertThat(Booleans.isFalse(false))
                .isTrue();
        Assertions.assertThat(Booleans.isFalse(Boolean.FALSE))
                .isTrue();
    }

    @Test
    void isFalse_shouldReturnFalseWhenValueIsNull() {
        Assertions.assertThat(Booleans.isFalse(null))
                .isFalse();
    }

    @Test
    void isNullOrFalse_shouldReturnFalseWhenValueIsTrue() {
        Assertions.assertThat(Booleans.isNullOrFalse(true))
                .isFalse();
        Assertions.assertThat(Booleans.isNullOrFalse(Boolean.TRUE))
                .isFalse();
    }

    @Test
    void isNullOrFalse_shouldReturnTrueWhenValueIsFalse() {
        Assertions.assertThat(Booleans.isNullOrFalse(false))
                .isTrue();
        Assertions.assertThat(Booleans.isNullOrFalse(Boolean.FALSE))
                .isTrue();
    }

    @Test
    void isNullOrFalse_shouldReturnTrueWhenValueIsNull() {
        Assertions.assertThat(Booleans.isNullOrFalse(null))
                .isTrue();
    }
}
