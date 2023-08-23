package org.navistack.framework.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractErrCategoryTest {

    @Test
    void name_shouldReturnAsIs() {
        FlightsErrCategory category = new FlightsErrCategory();
        assertThat(category.name())
                .isEqualTo("flights");
    }

    @Test
    void message_shouldReturnAsIsWhenValueExists() {
        FlightsErrCategory category = new FlightsErrCategory();
        assertThat(category.message(FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal()))
                .isEqualTo("Nonexistent airport name in request");
    }

    @Test
    void message_shouldReturnDefaultMessageWhenValueNotExists() {
        FlightsErrCategory category = new FlightsErrCategory();
        assertThat(category.message(-1))
                .isEqualTo("Unknown error -1");
    }
}
